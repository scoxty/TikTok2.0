package com.xty.middleware.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

// TODO: 限流策略以及代码编写待优化
@Component
public class RatedLimiter {
    @Autowired
    private RedisTemplate redisTemplate;

    // 使用 System.currentTimeMillis() 代替 time.Now
    private static final Long RESET_BUCKET_INTERNAL = 1000 * 60L; // 重置令牌桶的时间间隔为1min
    private static final Long INTERNAL_PER_PERMIT = 1000L; // 每个令牌产生的时间为1s
    private static final Long BUCKET_MAX_TOKEN = 60L; // 最大令牌数量
    private static final Long INIT_TOKEN = 60L; // 初始桶内得令牌数
    private static final String TOKEN_BUCKET = "tokenBucket"; // key名

    private static final String lua =
            "--[[\n" +
            "  1. key - 令牌桶的 key\n" +
            "  2. intervalPerTokens - 生成令牌的间隔(ms)\n" +
            "  3. curTime - 当前时间\n" +
            "  4. initTokens - 令牌桶初始化的令牌数\n" +
            "  5. bucketMaxTokens - 令牌桶的上限\n" +
            "  6. resetBucketInterval - 重置桶内令牌的时间间隔\n" +
            "  7. currentTokens - 当前桶内令牌数\n" +
            "  8. bucket - 当前 key 的令牌桶对象\n" +
            "]] --\n" +
            "\n" +
            "local key = KEYS[1]\n" +
            "local intervalPerTokens = tonumber(ARGV[1])\n" +
            "local curTime = tonumber(ARGV[2])\n" +
            "local initTokens = tonumber(ARGV[3])\n" +
            "local bucketMaxTokens = tonumber(ARGV[4])\n" +
            "local resetBucketInterval = tonumber(ARGV[5])\n" +
            "\n" +
            "local bucket = redis.call('hgetall', key)\n" +
            "local currentTokens\n" +
            "\n" +
            "-- 若当前桶未初始化,先初始化令牌桶\n" +
            "if table.maxn(bucket) == 0 then\n" +
            "    -- 初始桶内令牌\n" +
            "    currentTokens = initTokens\n" +
            "    -- 设置桶最近的填充时间是当前\n" +
            "    redis.call('hset', key, 'lastRefillTime', curTime)\n" +
            "    -- 初始化令牌桶的过期时间, 设置为间隔的 1.5 倍\n" +
            "    redis.call('pexpire', key, resetBucketInterval * 1.5)\n" +
            "\n" +
            "-- 若桶已初始化,开始计算桶内令牌\n" +
            "-- 为什么等于 4 ? 因为有两对 field, 加起来长度是 4\n" +
            "-- { \"lastRefillTime(上一次更新时间)\",\"curTime(更新时间值)\",\"tokensRemaining(当前保留的令牌)\",\"令牌数\" }\n" +
            "elseif table.maxn(bucket) == 4 then\n" +
            "\n" +
            "    -- 上次填充时间\n" +
            "    local lastRefillTime = tonumber(bucket[2])\n" +
            "    -- 剩余的令牌数\n" +
            "    local tokensRemaining = tonumber(bucket[4])\n" +
            "\n" +
            "    -- 当前时间大于上次填充时间\n" +
            "    if curTime > lastRefillTime then\n" +
            "\n" +
            "        -- 拿到当前时间与上次填充时间的时间间隔\n" +
            "        -- 举例理解: curTime = 2620 , lastRefillTime = 2000, intervalSinceLast = 620\n" +
            "        local intervalSinceLast = curTime - lastRefillTime\n" +
            "\n" +
            "        -- 如果当前时间间隔 大于 令牌的生成间隔\n" +
            "        -- 举例理解: intervalSinceLast = 620, resetBucketInterval = 1000\n" +
            "        if intervalSinceLast > resetBucketInterval then\n" +
            "\n" +
            "            -- 将当前令牌填充满\n" +
            "            currentTokens = initTokens\n" +
            "\n" +
            "            -- 更新重新填充时间\n" +
            "            redis.call('hset', key, 'lastRefillTime', curTime)\n" +
            "            \n" +
            "        -- 如果当前时间间隔 小于 令牌的生成间隔\n" +
            "        else\n" +
            "\n" +
            "            -- 可授予的令牌 = 向下取整数( 上次填充时间与当前时间的时间间隔 / 两个令牌许可之间的时间间隔 )\n" +
            "            -- 举例理解 : intervalPerTokens = 200 ms , 令牌间隔时间为 200ms\n" +
            "            --           intervalSinceLast = 620 ms , 当前距离上一个填充时间差为 620ms\n" +
            "            --           grantedTokens = 620/200 = 3.1 = 3\n" +
            "            local grantedTokens = math.floor(intervalSinceLast / intervalPerTokens)\n" +
            "\n" +
            "            -- 可授予的令牌 > 0 时\n" +
            "            -- 举例理解 : grantedTokens = 620/200 = 3.1 = 3\n" +
            "            if grantedTokens > 0 then\n" +
            "\n" +
            "                -- 生成的令牌 = 上次填充时间与当前时间的时间间隔 % 两个令牌许可之间的时间间隔\n" +
            "                -- 举例理解 : padMillis = 620%200 = 20\n" +
            "                --           curTime = 2620\n" +
            "                --           curTime - padMillis = 2600\n" +
            "                local padMillis = math.fmod(intervalSinceLast, intervalPerTokens)\n" +
            "\n" +
            "                -- 将当前令牌桶更新到上一次生成时间\n" +
            "                redis.call('hset', key, 'lastRefillTime', curTime - padMillis)\n" +
            "            end\n" +
            "\n" +
            "            -- 更新当前令牌桶中的令牌数\n" +
            "            -- Math.min(根据时间生成的令牌数 + 剩下的令牌数, 桶的限制) => 超出桶最大令牌的就丢弃\n" +
            "            currentTokens = math.min(grantedTokens + tokensRemaining, bucketMaxTokens)\n" +
            "        end\n" +
            "    else\n" +
            "        -- 如果当前时间小于或等于上次更新的时间, 说明刚刚初始化, 当前令牌数量等于桶内令牌数\n" +
            "        -- 不需要重新填充\n" +
            "        currentTokens = tokensRemaining\n" +
            "    end\n" +
            "end\n" +
            "\n" +
            "-- 如果当前桶内令牌小于 0,抛出异常\n" +
            "assert(currentTokens >= 0)\n" +
            "\n" +
            "-- 如果当前令牌 == 0 ,更新桶内令牌, 返回 0\n" +
            "if currentTokens == 0 then\n" +
            "    redis.call('hset', key, 'tokensRemaining', currentTokens)\n" +
            "    return 0\n" +
            "else\n" +
            "    -- 如果当前令牌 大于 0, 更新当前桶内的令牌 -1 , 再返回当前桶内令牌数\n" +
            "    redis.call('hset', key, 'tokensRemaining', currentTokens - 1)\n" +
            "    return currentTokens\n" +
            "end\n";

    public boolean acquireBucketToken(String ip) {
        String key = TOKEN_BUCKET + Keys.DELIMITER + ip;

        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setScriptText(lua);
        script.setResultType(Long.class);

        ArrayList<String> keys = new ArrayList<>();
        keys.add(key);

        Long cnt = (Long) redisTemplate.execute(script,
                keys,
                INTERNAL_PER_PERMIT,
                System.currentTimeMillis(),
                INIT_TOKEN,
                BUCKET_MAX_TOKEN,
                RESET_BUCKET_INTERNAL);

        return cnt > 0;
    }

}
