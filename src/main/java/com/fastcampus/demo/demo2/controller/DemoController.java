package com.fastcampus.demo.demo2.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Martin
 * @since 2021/03/04
 */
@RestController
public class DemoController {
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/test")
    @HystrixCommand(commandProperties = {},
    fallbackMethod = "fallback")
    public String demo() {
        System.out.println(">>> execute /test");
        return restTemplate.getForObject("http://localhost:8040/remote", String.class);
    }

    public String fallback() {
        return "Circuit Error";
    }
}
