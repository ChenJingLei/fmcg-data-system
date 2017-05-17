package com.john.cloud.provider;

import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cjl20 on 2017/5/15.
 */

public class CrawlerTest implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Test
    public void RuegularTest() throws Exception {

        Spider.create(new CrawlerTest()).addUrl("https://so.m.jd.com/ware/searchList.action?_format_=json&stock=0&sort=1&page=1&keyword=食用调和油").start();
    }

    int i = 2;

    @Override
    public void process(Page page) {

        page.addTargetRequest("https://so.m.jd.com/ware/searchList.action?_format_=json&stock=0&sort=1&page=" + i + "&keyword=食用调和油");
        i++;
    }

    @Override
    public Site getSite() {
        return site;
    }
}
