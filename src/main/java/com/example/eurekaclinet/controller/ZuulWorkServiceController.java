package com.example.eurekaclinet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZuulWorkServiceController {
    private static final Logger logger = LoggerFactory.getLogger(ZuulWorkServiceController.class);
    @Value("${server.port}")
    private int port;

    @RequestMapping("zuulwork")
    public String zuulwork() {
        String greeting = String.format("zuul work done. from server port is [%d]", port);
        return greeting;
    }

}
