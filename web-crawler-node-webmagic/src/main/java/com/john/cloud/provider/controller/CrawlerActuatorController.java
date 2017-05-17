package com.john.cloud.provider.controller;

import com.john.cloud.provider.crawlermonitor.SpringSpiderStatusMXBean;
import com.john.cloud.provider.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjl20 on 2017/5/17.
 */
@RestController
@RequestMapping("/actuator")
public class CrawlerActuatorController {

    @Autowired
    private SpiderService spiderService;


    @RequestMapping(value = "/crawlers", method = RequestMethod.GET)
    public List<SpringSpiderStatusMXBean> getCrawlers() {
        return spiderService.getSpiderStatuses();
    }

    @RequestMapping(value = "/crawlers/{name}", method = RequestMethod.GET)
    public List<SpringSpiderStatusMXBean> getCrawlersbyName(@PathVariable("name") String name) {
        List<SpringSpiderStatusMXBean> spiderStatusMXBeans = new ArrayList<>();
        for (SpringSpiderStatusMXBean statusMXBean : spiderService.getSpiderStatuses()) {
            if (statusMXBean.getName().contains(name)) {
                spiderStatusMXBeans.add(statusMXBean);
            }
        }
        return spiderStatusMXBeans;
    }


}
