package com.john.cloud.consumer.fallback;

import com.john.cloud.consumer.client.CrawlerExecuteFeignClient;
import com.john.crawler.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by cjl20 on 2017/5/17.
 */
@Component
public class HystrixClientFallback implements CrawlerExecuteFeignClient{

    private static final Logger logger = LoggerFactory.getLogger(HystrixClientFallback.class);

    @Override
    public String execute(Task task) {
        logger.info("Execute Function throw a Exception!!!");
        return task.toString();
    }
}
