package com.john.cloud.consumer.feign;

import com.john.crawler.icontroller.iCrawlerExecute;
import com.john.crawler.model.Task;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.john.cloud.consumer.feign.CrawlerExecuteFeignClient.HystrixClientFallbackFactory;

/**
 * Created by cjl20 on 2017/5/17.
 */

@FeignClient(name = "web-crawler-node", fallbackFactory = HystrixClientFallbackFactory.class)
public interface CrawlerExecuteFeignClient extends iCrawlerExecute {

    @Override
    @RequestMapping(value = "/node/executeByGoodName", method = RequestMethod.POST)
    String executeByGoodName(Task task);

    @Component
    class HystrixClientFallbackFactory implements FallbackFactory<CrawlerExecuteFeignClient> {

        private static final Logger logger = LoggerFactory.getLogger(HystrixClientFallbackFactory.class);

        @Override
        public CrawlerExecuteFeignClient create(Throwable cause) {
            return task -> {
                logger.info("Execute Function throw a Exception!!!");
                return cause.toString() + " (" + task.toString() + ")";
            };
        }
    }
}
