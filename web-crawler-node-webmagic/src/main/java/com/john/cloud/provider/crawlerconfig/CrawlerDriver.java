package com.john.cloud.provider.crawlerconfig;

import com.john.crawler.ipageprocessor.iPageCrawler;

/**
 * Created by cjl20 on 2017/5/14.
 */
public class CrawlerDriver {

//    public static <T> T driverForClass(Class<T> clazz) {
//        T crawlerDriver = null;
//        try {
//
//            crawlerDriver = clazz.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return crawlerDriver;
//    }

    public static iPageCrawler driverForClass(Class<?> clazz) {
        iPageCrawler crawlerDriver = null;
        try {

            crawlerDriver = (iPageCrawler) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return crawlerDriver;
    }

    public static Class<?> ClassForName(String platform) {
        Class<?> clazz = null;
        try {
            switch (platform) {
                case "JD":
                    clazz = Class.forName("com.john.cloud.provider.pageprocessor.JDRepoPageCrawler");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clazz;
    }

}
