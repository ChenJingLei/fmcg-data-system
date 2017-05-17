package com.john.cloud.consumer.controller;

import com.john.cloud.consumer.feign.CrawlerExecuteFeignClient;
import com.john.crawler.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by cjl20 on 2017/5/14.
 */
@RestController
public class InfoController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private CrawlerExecuteFeignClient crawlerExecuteFeignClient;


    @RequestMapping("/test")
    public String test() {
        Task task = new Task();
        task.setPlatform("JD");
        task.setGoodName("啤酒");
        return crawlerExecuteFeignClient.executeByGoodName(task);

    }

    @GetMapping("/instance-info")
    public List<ServiceInstance> showInfo() {
        List<ServiceInstance> localServiceInstances = this.discoveryClient.getInstances("web-crawler-master-feign-with-hystrix-stream");
        return localServiceInstances;
    }
}
