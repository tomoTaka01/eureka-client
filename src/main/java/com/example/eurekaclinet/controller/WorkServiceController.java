package com.example.eurekaclinet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

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
