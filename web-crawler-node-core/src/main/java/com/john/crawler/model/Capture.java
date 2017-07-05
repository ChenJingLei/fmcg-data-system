package com.john.crawler.model;

import java.util.HashSet;
import java.util.List;

/**
 * Created by cjl20 on 2017/5/14.
 */
public class Capture {

    private String platform;
    private HashSet<String> goods;

    public Capture() {
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public HashSet<String> getGoods() {
        return goods;
    }

    public void setGoods(HashSet<String> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Capture{" +
                "platform='" + platform + '\'' +
                ", goods=" + goods +
                '}';
    }
}
