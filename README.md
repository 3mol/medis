Medis Module

    medis_db
        medis_client
        medis_server
    client(ctl)

先完成medis_server部分的功能, 主要是处理命令, 执行命令的功能, 以及一些持久化方面的功能

```
redisServer {
// 数据 <key:val>
map<object,object> dict
// 数据对象的过期时间 <key: expiresTimestamp>
map<object,long> expires 
}
```

> 引用 《Redis 设计与实现》 黄健宏

---
##Road Map

### step 1

- 定义基础的实体结构
- 完成基础的命令
    - SET、GET
- 设置键的过期时间 9.4
    - 设置过期时间
    - 保存过期时间
    - 移除过期时间
    - 计算剩余生存时间
    - 判断是否为过期键
    - 定义过期键的删除策略（3种）

### step 2 持久化
- 实现SAVE命令
- 实现BGSAVE命令
- 实现自动BGSAVE
- 定义持久化RDB文件结构

### step 3 事件
- 文件事件
- 时间事件
...
  