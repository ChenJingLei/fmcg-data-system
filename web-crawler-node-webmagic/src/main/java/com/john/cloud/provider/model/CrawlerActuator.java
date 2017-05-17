package com.john.cloud.provider.model;

import java.util.Date;
import java.util.List;

/**
 * Created by cjl20 on 2017/5/17.
 */
public class CrawlerActuator {

    private String name;

    private String status;

    private int thread;

    private int totalPageCount;

    private int leftPageCount;

    private int successPageCount;

    private int errorPageCount;

    private List<String> errorPages;

    private Date startTime;

    private int pagePerSecond;

    public CrawlerActuator() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getThread() {
        return thread;
    }

    public void setThread(int thread) {
        this.thread = thread;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getLeftPageCount() {
        return leftPageCount;
    }

    public void setLeftPageCount(int leftPageCount) {
        this.leftPageCount = leftPageCount;
    }

    public int getSuccessPageCount() {
        return successPageCount;
    }

    public void setSuccessPageCount(int successPageCount) {
        this.successPageCount = successPageCount;
    }

    public int getErrorPageCount() {
        return errorPageCount;
    }

    public void setErrorPageCount(int errorPageCount) {
        this.errorPageCount = errorPageCount;
    }

    public List<String> getErrorPages() {
        return errorPages;
    }

    public void setErrorPages(List<String> errorPages) {
        this.errorPages = errorPages;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getPagePerSecond() {
        return pagePerSecond;
    }

    public void setPagePerSecond(int pagePerSecond) {
        this.pagePerSecond = pagePerSecond;
    }

    @Override
    public String toString() {
        return "CrawlerActuator{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", thread=" + thread +
                ", totalPageCount=" + totalPageCount +
                ", leftPageCount=" + leftPageCount +
                ", successPageCount=" + successPageCount +
                ", errorPageCount=" + errorPageCount +
                ", errorPages=" + errorPages +
                ", startTime=" + startTime +
                ", pagePerSecond=" + pagePerSecond +
                '}';
    }
}
