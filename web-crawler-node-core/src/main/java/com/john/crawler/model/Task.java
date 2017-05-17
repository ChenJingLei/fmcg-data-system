package com.john.crawler.model;

import java.io.Serializable;

/**
 * Created by cjl20 on 2017/5/14.
 */
public class Task implements Serializable {

    private String platform;
    private String goodName;

    public Task() {
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    @Override
    public String toString() {
        return "Task{" +
                "platform='" + platform + '\'' +
                ", goodName='" + goodName + '\'' +
                '}';
    }
}
