package com.john.crawler.icontroller;

import com.john.crawler.model.Task;

/**
 * Created by cjl20 on 2017/5/18.
 */
public interface iCrawlerNodeExecute extends iCrawlerExecute {

    /*
   * 用于node与node之间通讯
   */
    String executeByGoodURL(Task task);
}
