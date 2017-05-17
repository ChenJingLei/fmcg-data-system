package com.john.cloud.provider.crawlermonitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Spider;

import us.codecraft.webmagic.scheduler.MonitorableScheduler;

import java.util.Date;
import java.util.List;

/**
 * Created by cjl20 on 2017/5/17.
 */
public class SpringSpiderStatus implements SpringSpiderStatusMXBean {

    protected final Spider spider;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected final SpringMonitorSpiderListener monitorSpiderListener;
    private String goodName;

    public SpringSpiderStatus(Spider spider, SpringMonitorSpiderListener monitorSpiderListener) {
        this.spider = spider;
        this.monitorSpiderListener = monitorSpiderListener;
    }

    public SpringSpiderStatus(Spider spider, SpringMonitorSpiderListener monitorSpiderListener, String goodName) {
        this.spider = spider;
        this.monitorSpiderListener = monitorSpiderListener;
        this.goodName = goodName;
    }

    public String getName() {
        return this.spider.getUUID();
    }

    public int getLeftPageCount() {
        if (this.spider.getScheduler() instanceof MonitorableScheduler) {
            return ((MonitorableScheduler) this.spider.getScheduler()).getLeftRequestsCount(this.spider);
        } else {
            this.logger.warn("Get leftPageCount fail, try to use a Scheduler implement MonitorableScheduler for monitor count!");
            return -1;
        }
    }

    public int getTotalPageCount() {
        if (this.spider.getScheduler() instanceof MonitorableScheduler) {
            return ((MonitorableScheduler) this.spider.getScheduler()).getTotalRequestsCount(this.spider);
        } else {
            this.logger.warn("Get totalPageCount fail, try to use a Scheduler implement MonitorableScheduler for monitor count!");
            return -1;
        }
    }

    public int getSuccessPageCount() {
        return this.monitorSpiderListener.getSuccessCount().get();
    }

    public int getErrorPageCount() {
        return this.monitorSpiderListener.getErrorCount().get();
    }

    public List<String> getErrorPages() {
        return this.monitorSpiderListener.getErrorUrls();
    }

    public String getStatus() {
        return this.spider.getStatus().name();
    }

    public int getThread() {
        return this.spider.getThreadAlive();
    }

    public void start() {
        this.spider.start();
    }

    public void stop() {
        this.spider.stop();
    }

    public Date getStartTime() {
        return this.spider.getStartTime();
    }

    public int getPagePerSecond() {
        if (getStartTime() != null) {
            int runSeconds = (int) (System.currentTimeMillis() - this.getStartTime().getTime()) / 1000;
            return this.getSuccessPageCount() / runSeconds;
        }
        return -1;
    }

    public String getGoodName() {
        if (this.goodName != null) {
            return this.goodName;
        }
        return null;
    }
}
