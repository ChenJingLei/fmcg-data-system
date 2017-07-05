package com.john.crawler.model;

import java.util.List;

/**
 * Created by cjl20 on 2017/5/14.
 */
public class TaskCollection {

    private Long timeStamp;
    private List<Capture> captureList;

    public TaskCollection() {
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<Capture> getCaptureList() {
        return captureList;
    }

    public void setCaptureList(List<Capture> captureList) {
        this.captureList = captureList;
    }

    @Override
    public String toString() {
        return "TaskCollection{" +
                "timeStamp='" + timeStamp + '\'' +
                ", captureList=" + captureList +
                '}';
    }
}
