package com.example.eurekaclinet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class GreetingServiceController {
    private static final Logger logger = LoggerFactory.getLogger(GreetingServiceController.class);
    private final DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private int port;

    public GreetingServiceController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @RequestMapping("/hi/{name}")
    public String hiService(@PathVariable Optional<String> name) {
        String greeting = String.format("Hi %s! from port is [%d]", name.orElse("eureka"), port);
        return greeting;
    }

    @RequestMapping("/hello/{name}")
    public String helloService(@PathVariable String name) {
        String greeting = String.format("Hello %s from port is [%d]", name, port);
        return greeting;
    }
}
