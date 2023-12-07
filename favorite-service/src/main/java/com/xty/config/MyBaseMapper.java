package com.xty.config;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyBaseMapper<T> extends BaseMapper<T> {
    // 批量插入
    int insertBatchSomeColumn(@Param("list") List<T> batchList);
}