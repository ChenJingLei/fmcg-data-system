package com.john.cloud.provider.crawlermonitor;

import us.codecraft.webmagic.monitor.SpiderStatusMXBean;

import java.util.Date;

/**
 * Created by cjl20 on 2017/5/17.
 */
public interface SpringSpiderStatusMXBean extends SpiderStatusMXBean {

    String getGoodName();
}
