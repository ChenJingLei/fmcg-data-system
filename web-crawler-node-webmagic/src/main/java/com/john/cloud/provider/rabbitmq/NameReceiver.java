package com.john.cloud.provider.rabbitmq;

import com.john.cloud.provider.crawlerconfig.CrawlerDriver;
import com.john.cloud.provider.pageprocessor.JDRepoPageCrawler;
import com.john.cloud.provider.service.SpiderService;
import com.john.crawler.ipageprocessor.iPageCrawler;
import com.john.crawler.model.Task;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

/**
 * Created by cjl20 on 2017/5/14.
 */
@Component
@RabbitListener(queues = "name-node")
public class NameReceiver {

    @Autowired
    private SpiderService spiderService;

    @RabbitHandler
    public void process(Task task) throws Exception {

        Spider spider = spiderService.getSpider(CrawlerDriver.ClassForName(task.getPlatform()), task.getGoodName());
        iPageCrawler pageCrawler = CrawlerDriver.driverForClass(CrawlerDriver.ClassForName(task.getPlatform()));
        pageCrawler.executeByGoodName(spider, task.getGoodName().replaceAll(" ", "%20"));

    }
}
