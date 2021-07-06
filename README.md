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



