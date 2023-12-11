# 项目概述
TikTok2.0主要是对青训营项目使用Java语言进行重新设计和开发(主要实现本人负责部分)，基于SpringBoot、Dubbo、MySQL、Redis、RocketMQ、Mybatis-plus、本地缓存、Nacos、OSS等技术栈。项目的主要目标是实现基本功能的同时保持系统的高性能和可靠性。
# 整体架构
![image](https://github.com/scoxty/TikTok2.0/assets/95528203/591fae3a-1eaf-4045-b275-ec8424c26f3b)\
实现的模块说明:\
-API层：负责接受用户的请求，使用JWT签发及检验token，并对参数及文件做合法性检验\
-User Server ：负责登录、注册、用户信息\
-Video Server ：负责视频投稿并异步处理，提供feed流列表，个人主页的视频发布列表，并给用户模块提供作品数\
-Favorite Server：点赞/取消点赞操作、获取喜欢列表。并给用户模块提供获赞数、喜欢数，给视频模块提供视频点赞数，并判断用户是否点赞当前视频\
# 代码结构
```
|
├── api                               // API模块，提供外部接口
│   ├── src
│   │   └── main
│   │       ├── java
│   │       │   └── com
│   │       │       └── xty
│   │       │           ├── config    // 配置相关
│   │       │           ├── controller// 控制器，处理请求
│   │       │           ├── Interceptor// 拦截器，用于请求拦截
│   │       │           │   ├── count_limiter// 计数限制
│   │       │           │   ├── jwt         // JWT处理
│   │       │           │   └── rate_limiter_baseIp// 基于IP的速率限制
│   │       │           ├── middleware    // 中间件，处理请求和响应
│   │       │           │   └── redis     // Redis相关操作
│   │       │           └── response      // 响应处理
│   │       └── resources	// 	使用nacos配置中心读取配置，后面同理
│   │           └── ssl   // SSL配置
│   │ 		
│   └── target            // 编译生成的文件
├── constant                          // 常量定义模块
│   ├── src
│   │   └── main
│   │       └── java
│   │           └── com
│   │               └── xty
│   │                   └── business   // 业务相关的常量定义
├── favorite-service                  // 点赞服务模块
│   ├── src
│   │   └── main
│   │       ├── java
│   │       │   └── com
│   │       │       └── xty
│   │       │           ├── config     // 配置
│   │       │           ├── dal        // 数据访问层
│   │       │           │   └── mysql  // MySQL访问
│   │       │           │       ├── mapper // MyBatis映射
│   │       │           │       └── pojo   // 数据对象
│   │       │           ├── middleware// 中间件
│   │       │           │   ├── redis     // Redis操作
│   │       │           │   └── rocketmq  // RocketMQ消息队列
│   │       │           └── service   // 服务逻辑
│   │       └── resources
├── rpc                               // RPC模块，远程调用
│   ├── src
│   │   └── main
│   │       ├── java
│   │       │   └── com
│   │       │       └── xty
│   │       │           └── dubbo_gen  // Dubbo生成的代码
│   │       ├── proto                 // Protobuf定义
├── user-service                      // 用户服务模块
│   ├── src
│   │   └── main
│   │       ├── java
│   │       │   └── com
│   │       │       └── xty
│   │       │           ├── config     // 配置
│   │       │           ├── dal        // 数据访问层
│   │       │           │   └── mysql  // MySQL访问
│   │       │           ├── middleware// 中间件
│   │       │           │   └── redis     // Redis操作
│   │       │           └── service   // 服务逻辑
│   │       └── resources
├── utils                             // 工具模块，提供通用工具和功能
│   ├── src
│   │   └── main
│   │       ├── java
│   │       │   └── com
│   │       │       └── xty
│   │       │           └── [工具类]   // 各种工具类实现
└── video-service                     // 视频服务模块
    ├── src
    │   └── main
    │       ├── java
    │       │   └── com
    │       │       └── xty
    │       │           ├── config     // 配置
    │       │           ├── dal        // 数据访问层
    │       │           │   └── mysql  // MySQL访问
    │       │           ├── middleware// 中间件
    │       │           │   ├── redis     // Redis操作
    │       │           │   └── rocketmq  // RocketMQ消息队列
    │       │           └── service   // 服务逻辑
    │       └── resources

```




