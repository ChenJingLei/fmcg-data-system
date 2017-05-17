package com.john.cloud.provider.service;

import com.john.cloud.provider.crawlermonitor.SpringMonitorSpiderListener;
import com.john.cloud.provider.crawlermonitor.SpringSpiderStatus;
import com.john.cloud.provider.crawlermonitor.SpringSpiderStatusMXBean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.SpiderListener;

import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjl20 on 2017/5/16.
 */
@Service
public class SpiderService {

    private List<SpringSpiderStatusMXBean> spiderStatuses = new ArrayList();

    @Value("${spider.threadNum}")
    private int threadNum = 5;

    @PostConstruct
    public void init() {
        //读取所有PageProcess信息
    }

    public Spider getSpider(Class<?> clazz, String goodName) {
        Spider spider = null;
        try {
            PageProcessor p = (PageProcessor) clazz.newInstance();
            spider = Spider.create(p).thread(threadNum).addPipeline(new ConsolePipeline());
            SpringMonitorSpiderListener monitorSpiderListener = new SpringMonitorSpiderListener();
            if (spider.getSpiderListeners() == null) {
                List<SpiderListener> spiderListeners = new ArrayList();
                spiderListeners.add(monitorSpiderListener);
                spider.setSpiderListeners(spiderListeners);
            } else {
                spider.getSpiderListeners().add(monitorSpiderListener);
            }
            SpringSpiderStatusMXBean spiderStatusMBean = new SpringSpiderStatus(spider, monitorSpiderListener, goodName);
            this.spiderStatuses.add(spiderStatusMBean);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return spider;
    }

    public List<SpringSpiderStatusMXBean> getSpiderStatuses() {
        for (SpringSpiderStatusMXBean bean : spiderStatuses) {
            if (bean.getStatus().equals("Stopped")) {
                //spiderStatuses.remove(bean);
            }
        }
        return spiderStatuses;
    }

    public SpiderService() {
    }

    @PreDestroy
    public void destory() {
        //销毁爬虫
        System.out.println("-------------3-------------");
    }
}
