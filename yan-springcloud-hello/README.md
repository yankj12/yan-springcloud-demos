# spring-boot-demo

SpringBoot结合一些中间件的demo

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
