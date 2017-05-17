package com.john.cloud.consumer.controller;

import com.john.cloud.consumer.client.CrawlerExecuteFeignClient;
import com.john.cloud.consumer.model.Capture;

import com.john.cloud.consumer.model.TaskCollection;

import com.john.crawler.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cjl20 on 2017/5/14.
 */
@RestController
@RequestMapping("/master")
public class TaskCrawlerController {

    @Autowired
    private CrawlerExecuteFeignClient crawlerExecuteFeignClient;

    @RequestMapping(value = "/batch-execute", method = RequestMethod.POST)
    public void execute(@RequestBody TaskCollection taskCollection) {
        for (Capture capture : taskCollection.getCaptureList()) {
            for (String goodName : capture.getGoods()) {
                Task task = new Task();
                task.setPlatform(capture.getPlatform());
                task.setGoodName(goodName);
                crawlerExecuteFeignClient.execute(task);
            }
        }
    }

}
