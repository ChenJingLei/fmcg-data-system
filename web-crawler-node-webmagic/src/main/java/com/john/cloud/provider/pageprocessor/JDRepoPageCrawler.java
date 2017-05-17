package com.john.cloud.provider.pageprocessor;

import com.john.cloud.provider.model.JDgood;
import com.john.cloud.provider.selector.CrawlerJsonPathSelector;
import com.john.crawler.ipageprocessor.iPageCrawler;
import org.apache.commons.collections.CollectionUtils;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by cjl20 on 2017/5/14.
 */
@Component
public class JDRepoPageCrawler implements iPageCrawler, PageProcessor {

    private static final String SEARCH_LIST_URL = "https://so\\.m\\.jd\\.com/ware/searchList\\.action\\?_format_=json&stock=0&sort=1&page=\\d+&keyword=.*";

    public JDRepoPageCrawler() {
    }

    /*
     * https://so.m.jd.com/ware/searchList.action?_format_=json&stock=0&sort=1&page=1&keyword=商品名称
     */
    public void executeByGoodName(Spider spider, String goodName) throws Exception {
        this.executeByURL(
                spider,
                SEARCH_LIST_URL
                        .replace("\\", "")
                        .replace("d+", "1")
                        .replace(".*", goodName));
    }

    public void executeByURL(Spider spider, String url) throws Exception {
        spider.addUrl(url);
        spider.start();
    }


    //每一页-》先拿商品信息
    //再拿每一个商品具体信息
    @Override
    public void process(Page page) {

        if (page.getUrl().regex(SEARCH_LIST_URL).match()) {
            int pageIndex = Integer.valueOf(page.getUrl().regex("page=\\d+").regex("\\d+").get());
            String value = new CrawlerJsonPathSelector("$.value").select(page.getRawText());
            int wareCount = Integer.valueOf(new CrawlerJsonPathSelector("$.wareList.wareCount").select(value));

            List<JDgood> wareList = new CrawlerJsonPathSelector("$.wareList.wareList[*]").selectList(value, JDgood.class);
            if (CollectionUtils.isNotEmpty(wareList)) {
                page.putField("wareList", wareList);
            }
            if (pageIndex <= (wareCount / 10) + 1) {
                page.addTargetRequest(page.getUrl().replace("page=" + pageIndex, "page=" + (pageIndex + 1)).get());
            }
        }
    }

    @Override
    public Site getSite() {
        Site site = Site.me();
        site.setCharset("utf-8");
        site.setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.6 Safari/537.36");
        site.addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        site.addHeader("accept-encoding", "gzip, deflate, sdch, br");
        site.addHeader("accept-language", "zh-CN,zh;q=0.8");
        site.addHeader("cache-control", "max-age=0");
        site.addHeader("upgrade-insecure-requests", "1");
        site.setTimeOut(3000);
        site.setRetryTimes(3);
        site.setCycleRetryTimes(3);
//        List<String[]> poolHosts = new ArrayList<String[]>();
//        poolHosts.add(new String[]{"cjl4565", "s3526op4", "113.140.41.95", "16816"});
//        poolHosts.add(new String[]{"cjl4565", "s3526op4", "113.140.41.60", "16816"});
//        poolHosts.add(new String[]{"cjl4565", "s3526op4", "211.149.159.44", "16816"});
//        poolHosts.add(new String[]{"cjl4565", "s3526op4", "120.25.71.27", "16816"});
//        poolHosts.add(new String[]{"cjl4565", "s3526op4", "121.42.140.113", "16816"});
//        site.setHttpProxyPool(poolHosts, false);
        return site;
    }

}
