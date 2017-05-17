package com.john.crawler.ipageprocessor;

import us.codecraft.webmagic.Spider;

/**
 * Created by cjl20 on 2017/5/14.
 */
public interface iPageCrawler {

    void execute(Spider spider, String goodName, int threadNum) throws Exception;
}
