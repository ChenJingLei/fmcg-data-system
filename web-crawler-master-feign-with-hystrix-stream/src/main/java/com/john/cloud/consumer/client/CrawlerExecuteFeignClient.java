package com.john.cloud.consumer.client;

import com.john.cloud.consumer.fallback.HystrixClientFallback;
import com.john.crawler.icontroller.iCrawlerExecute;
import com.john.crawler.model.Task;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by cjl20 on 2017/5/17.
 */

@FeignClient(name = "web-crawler-node", fallback = HystrixClientFallback.class)
public interface CrawlerExecuteFeignClient extends iCrawlerExecute {

    @RequestMapping(value = "/node/execute", method = RequestMethod.POST)
    @Override
    String execute(Task task);
}
