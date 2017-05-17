package com.john.crawler.icontroller;

import com.john.crawler.model.ExecuteReturn;
import com.john.crawler.model.Task;

/**
 * Created by cjl20 on 2017/5/14.
 */
public interface iCrawlerExecute {

    /*
    * 用于master与node之间通讯
    */
    String executeByGoodName(Task task);



}
