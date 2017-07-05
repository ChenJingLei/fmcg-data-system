package com.john.crawler.model;

import java.util.List;

/**
 * Created by cjl20 on 2017/5/23.
 */
public class UserTask {

    private String timeStamp;
    private List<Task> goods;
    private String tasktime;

    public UserTask() {
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<Task> getGoods() {
        return goods;
    }

    public void setGoods(List<Task> goods) {
        this.goods = goods;
    }

    public String getTasktime() {
        return tasktime;
    }

    public void setTasktime(String tasktime) {
        this.tasktime = tasktime;
    }

    @Override
    public String toString() {
        return "UserTask{" +
                "timeStamp='" + timeStamp + '\'' +
                ", goods=" + goods +
                ", tasktime='" + tasktime + '\'' +
                '}';
    }
}
