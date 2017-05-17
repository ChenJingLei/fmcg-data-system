package com.john.cloud.provider.pipeline;

import com.john.cloud.provider.model.JDgood;
import com.john.cloud.provider.repositories.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * Created by cjl20 on 2017/5/16.
 */
@Component("GoodInfoDaoPipeline")
public class GoodInfoDaoPipeline implements PageModelPipeline<JDgood> {



    @Override
    public void process(JDgood jDgood, Task task) {

    }
}
