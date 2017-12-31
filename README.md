## Spring Boot eureka client  
  * as of 2017-12-31, SpringBoot version 2.0.0.M7
  * for eureka server to see:https://github.com/tomoTaka01/eureka-server

  * bootstrap.yml(for registered application name)
```yml
spring:
  application:
    name: greeting-service
```

  * application.yml(for eureka server)
```yml
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```

  * GreetingServiceController.java
```java
public class GreetingServiceController {
  private final DiscoveryClient discoveryClient;
  @Value("${server.port}")
  private int port;

  public GreetingServiceController(DiscoveryClient discoveryClient) {
    this.discoveryClient = discoveryClient;
  }
  @RequestMapping("/hello/{name}")
    public String helloService(@PathVariable String name) {
      String greeting = String.format("Hello %s from port is [%d]", name, port);
      return greeting;
    }
}
```

  * to start appication with specific port

```
--server.port=8001
--server.port=8002
```


