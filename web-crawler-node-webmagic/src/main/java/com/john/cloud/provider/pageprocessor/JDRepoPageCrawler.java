package com.john.cloud.provider.pageprocessor;

import com.john.cloud.provider.model.JDgood;
import com.john.cloud.provider.pipeline.JDGoodInfoDaoPipeline;
import com.john.cloud.provider.rabbitmq.Sender;
import com.john.cloud.provider.selector.CrawlerJsonPathSelector;
import com.john.crawler.ipageprocessor.iPageCrawler;
import com.john.crawler.model.Task;
import org.apache.commons.collections.CollectionUtils;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by cjl20 on 2017/5/14.
 */
@Component
public class JDRepoPageCrawler implements iPageCrawler, PageProcessor {

    private static final String SEARCH_LIST_URL = "https://so\\.m\\.jd\\.com/ware/searchList\\.action\\?_format_=json&stock=0&sort=1&page=\\d+&keyword=.*";
    private static final String GOOD_DETAIL_JSON_URL = "https://item\\.m\\.jd.com/ware/[a-zA-Z]+\\.json\\?wareId=.*";
    private static final String GOOD_DETAIL_URL = "https://item\\.m\\.jd\\.com/product/[a-zA-Z0-9]+\\.html";

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private JDGoodInfoDaoPipeline jdGoodInfoDaoPipeline;

    public JDRepoPageCrawler() {
    }

    int perCount = 10;

    /*
     * https://so.m.jd.com/ware/searchList.action?_format_=json&stock=0&sort=1&page=1&keyword=商品名称
     */
    public void executeByGoodName(Spider spider, String goodName) throws Exception {
        spider.addPipeline(new FilePipeline("D:\\data1\\"));
        this.executeByURL(
                spider,
                SEARCH_LIST_URL
                        .replace("\\", "")
                        .replace("d+", "1")
                        .replace(".*", goodName));
    }

    public void executeByURL(Spider spider, String url) throws Exception {
        if (Pattern.matches("https://item.jd.com/.*", url)) {
            //电脑版
            String wareId = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
            url = "https://item.m.jd.com/product/" + wareId + ".html";
            spider.addUrl("https://item.m.jd.com/ware/detail.json?wareId=" + wareId);
        }
        spider
                .addUrl(url)
                .addPipeline(jdGoodInfoDaoPipeline);
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
                for (int i = 0; i < wareList.size(); i++) {
                    wareList.get(i).setSalesRate((pageIndex - 1) * 10 + i + 1);
                }
                page.putField("wareList", wareList);
            }

            if ((wareCount / perCount) + 1 > perCount && pageIndex == 1) {
                for (int i = 1 + perCount; i <= (wareCount / perCount) + 1; i = i + 10) {
                    Task task = new Task();
                    task.setPlatform("JD");
                    task.setGoodName(page.getUrl().replace("page=" + pageIndex, "page=" + i).get());
                    this.amqpTemplate.convertAndSend("url-node", task);
                }
            }

            if (pageIndex % perCount == 1) {
                for (int i = pageIndex; i < pageIndex + perCount; i++) {
                    page.addTargetRequest(page.getUrl().replace("page=" + pageIndex, "page=" + i).get());
                }
            }
        } else if (page.getUrl().regex(GOOD_DETAIL_URL).match()) {

            JDgood jDgood = new JDgood();
            jDgood.setWareId(page.getHtml().xpath("//input[@id='currentWareId']//@value").get());
            jDgood.setWname(page.getHtml().xpath("//input[@id='goodName']//@value").get());
            jDgood.setJdPrice(page.getHtml().xpath("//input[@id='jdPrice']//@value").get());
            jDgood.setGoodUrl(page.getUrl().replace("\\.m", "").replace("/product", "").get());
            page.putField("ware", jDgood);

        } else if (page.getUrl().regex(GOOD_DETAIL_JSON_URL).match()) {
            JDgood jDgood = new JDgood();
            jDgood.setGoodUrl("https://item.jd.com/" + page.getUrl().get().substring(page.getUrl().get().lastIndexOf("=") + 1) + ".html");

            String code = new CrawlerJsonPathSelector("$.ware.wi.code").select(page.getRawText());
//            jDgood.setBrand(new CrawlerJsonPathSelector("$[0][\"主体\"][0][\"品牌\"]").select(code));
//            jDgood.setType(new CrawlerJsonPathSelector("$[0][\"主体\"][2][\"类别\"]").select(code));
            page.putField("ware", jDgood);

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
