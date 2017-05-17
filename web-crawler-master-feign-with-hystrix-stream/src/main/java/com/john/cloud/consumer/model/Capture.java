package com.john.cloud.consumer.model;

import java.util.List;

/**
 * Created by cjl20 on 2017/5/14.
 */
public class Capture {

    private String platform;
    private List<String> goods;

    public Capture() {
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public List<String> getGoods() {
        return goods;
    }

    public void setGoods(List<String> goods) {
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
