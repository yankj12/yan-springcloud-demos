# yan-springcloud-demos

SpringCloud的demo


|微服务名称|端口|备注|
|---|---|---|
|yan-springcloud-eurekaserver|8000|服务注册发现，eureka服务端|
|yan-springcloud-echo|9000|服务提供方|
|yan-springcloud-hello|9001|服务调用方，集成了断路器|
|yan-springcloud-hystrix-dashboard|9092|hystrix的dashboard|

hystrix-dashboard并不需要集成到某个服务客户端之中， hystrix-dashboard 只是提供了一个监控页面

## 常见问题

### 问题1

Caused by: java.lang.ClassNotFoundException: feign.Feign$Builder 正确解决方法

因为pom缺少依赖，只要加入

```XML
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-feign</artifactId>
</dependency>

```

### 问题2：服务消费者调用了已经下线的服务提供者或者服务提供者已经上线，但是消费者还认为处于下线状态

SpringCloud中eureka配置心跳和剔除下线的服务的时间

默认的springCloud中eureka注册中心在服务下线时表现的非常不灵敏，和dubbo的zk注册中心差异较大，eureka设计的本意是在服务不会频繁上下线和网络稳定的内网，这种设计在生产环境是没什么问题的，但在开发和测试环境却会导致经常调用到已经下线的服务提供者，可以加上如下配置来解决（建议配合profile）

1. eureka server中的application.properties

```properties
#此配置建议只试用开发和测试环境
#驱逐下线的服务，间隔,5秒，默认是60
#org.springframework.cloud.netflix.eureka.server.EurekaServerConfigBean.evictionIntervalTimerInMs
eureka.server.evictionIntervalTimerInMs=5000
```

参见类：com.netflix.eureka.registry.AbstractInstanceRegistry

2. 服务提供者的application.properties

```properties
#此配置建议只试用开发和测试环境#心跳间隔时间,默认是30秒
#org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean.leaseRenewalIntervalInSeconds
eureka.instance.leaseRenewalIntervalInSeconds=2
#最后一次心跳时间后leaseExpirationDurationInSeconds秒就认为是下线了，默认是90秒
#org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean.leaseExpirationDurationInSeconds
eureka.instance.leaseExpirationDurationInSeconds=6
```

详细配置项见：org.springframework.cloud.netflix.eureka.EurekaClientConfigBean

## 参考资料

- [eureka服务器端](./yan-springcloud-eurekaserver/README.md)

- [微服务服务提供端](./yan-springcloud-echo/README.md)

- [服务调用端，通过feign调用](./yan-springcloud-hello/README.md)

- [Hystrix Dashboard：断路器执行监控](https://cloud.tencent.com/developer/article/1512144)

- [熔断监控Hystrix Dashboard和Turbine](http://www.ityouknow.com/springcloud/2017/05/18/hystrix-dashboard-turbine.html)
