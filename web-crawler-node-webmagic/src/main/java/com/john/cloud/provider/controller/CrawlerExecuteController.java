package com.john.cloud.provider.controller;

import com.john.cloud.provider.rabbitmq.Sender;
import com.john.cloud.provider.service.SpiderService;
import com.john.crawler.icontroller.iCrawlerExecute;
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
@RequestMapping("/node")
public class CrawlerExecuteController implements iCrawlerExecute {

    @Autowired
    private Sender sender;

    @RequestMapping(value = "/execute", method = RequestMethod.POST)
    public String execute(@RequestBody Task task) {
        try {
            sender.send(task);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "Success";
    }
}
