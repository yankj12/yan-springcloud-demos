# yan-springcloud-demos

SpringCloud的demo


|微服务名称|端口|备注|
|---|---|---|
|yan-springcloud-eurekaserver|8000|服务注册发现，eureka服务端|
|yan-springcloud-echo|9000|服务提供方|
|yan-springcloud-hello|9001|服务调用方，集成了断路器|


## 目录

[eureka服务器端](./yan-springcloud-eurekaserver/README.md)

[微服务服务提供端](./yan-springcloud-echo/README.md)

[服务调用端，通过feign调用](./yan-springcloud-hello/README.md)
