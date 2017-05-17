package com.john.cloud.provider.controller;

import com.john.cloud.provider.rabbitmq.Sender;
import com.john.crawler.icontroller.iCrawlerNodeExecute;
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
public class CrawlerExecuteController implements iCrawlerNodeExecute {

    @Autowired
    private Sender sender;

    @RequestMapping(value = "/executeByGoodName", method = RequestMethod.POST)
    public String executeByGoodName(@RequestBody Task task) {
        try {
            sender.sendByName(task);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "Success";
    }

    @RequestMapping(value = "/executeByGoodURL", method = RequestMethod.POST)
    public String executeByGoodURL(@RequestBody Task task) {
        try {
            sender.sendByURL(task);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "Success";
    }

}
