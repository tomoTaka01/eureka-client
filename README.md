## Spring Boot eureka client  
  * as of 2018-01-02, SpringBoot version 2.0.0.M7
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
public class GreetingServiWorkServiceControllerceController {
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

### for Hystrix(Circuit Breaker)

  * WorkServiceController.java

```java
@RestController
public class WorkServiceController {
    private static final Logger logger = LoggerFactory.getLogger(WorkServiceController.class);
    @Value("${server.port}")
        private int port;
    private Random random = new Random();

    @RequestMapping("/work")
        public String work() {
            int i = random.nextInt(5) + 1;
            logger.info("start work with {} sec", i);
            try {
                TimeUnit.SECONDS.sleep(Long.valueOf(i));
            } catch (InterruptedException e) {
                logger.error("work error", e);
                throw new RuntimeException(e.getMessage());
            }
            String work = String.format("%d sec work done. port=[%d]", i, port);
            return work;
        }
}
```

```
curl "http://localhost:8001/work"
```

  * The above reruen the response like 

```
1 sec work done. port=[8001]
2 sec work done. port=[8001]
3 sec work done. port=[8001]
4 sec work done. port=[8001]
5 sec work done. port=[8001]
```

  * to start appication with specific port

```
--server.port=8001
--server.port=8002
```
