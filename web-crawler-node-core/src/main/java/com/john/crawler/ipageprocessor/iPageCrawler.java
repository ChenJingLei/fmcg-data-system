package com.john.crawler.ipageprocessor;

import us.codecraft.webmagic.Spider;

/**
 * Created by cjl20 on 2017/5/14.
 */
public interface iPageCrawler {

    void executeByGoodName(Spider spider, String goodName) throws Exception;

    void executeByURL(Spider spider, String url) throws Exception;
}
