package com.john.cloud.provider.crawlermonitor;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.SpiderListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cjl20 on 2017/5/17.
 */
public class SpringMonitorSpiderListener implements SpiderListener {
    private final AtomicInteger successCount = new AtomicInteger(0);
    private final AtomicInteger errorCount = new AtomicInteger(0);
    private List<String> errorUrls = Collections.synchronizedList(new ArrayList());

    public SpringMonitorSpiderListener() {
    }

    public void onSuccess(Request request) {
        this.successCount.incrementAndGet();
    }

    public void onError(Request request) {
        this.errorUrls.add(request.getUrl());
        this.errorCount.incrementAndGet();
    }

    public AtomicInteger getSuccessCount() {
        return this.successCount;
    }

    public AtomicInteger getErrorCount() {
        return this.errorCount;
    }

    public List<String> getErrorUrls() {
        return this.errorUrls;
    }
}
