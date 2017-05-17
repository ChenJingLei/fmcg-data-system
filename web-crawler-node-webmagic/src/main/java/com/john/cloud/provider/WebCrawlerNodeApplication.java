package com.john.cloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by cjl20 on 2017/5/14.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class WebCrawlerNodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebCrawlerNodeApplication.class, args);
    }
}
