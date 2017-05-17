package com.john.cloud.consumer.model;

import java.util.List;

/**
 * Created by cjl20 on 2017/5/14.
 */
public class TaskCollection {

    private String timeStamp;
    private List<Capture> captureList;

    public TaskCollection() {
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
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
