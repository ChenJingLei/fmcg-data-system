package com.john.cloud.provider.pipeline;

import com.john.cloud.provider.model.Good;
import com.john.cloud.provider.model.JDgood;
import com.john.cloud.provider.repositories.GoodRepository;
import com.john.cloud.provider.repositories.JDgoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;


/**
 * Created by cjl20 on 6/4/2017.
 */
@Component
public class JDGoodInfoDaoPipeline implements Pipeline {

    @Autowired
    private GoodRepository goodRepository;

    @Autowired
    private JDgoodRepository jDgoodRepository;

    @Override
    public void process(ResultItems resultItems, Task task) {
        JDgood jDgood = resultItems.get("ware");

        if (jDgood != null) {
            Good good = goodRepository.findByGoodUrl(jDgood.getGoodUrl());
            good = good == null ? new Good() : good;
            if (good.getWname() == null) good.setWname(jDgood.getWname());
            if (good.getImageUrl() == null) good.setImageUrl(jDgood.getImageurl());
//            if (!jDgood.getJdPrice().isEmpty() || jDgood.getJdPrice().matches("[0-9.]+")) {
//                good.setPrice(Double.valueOf(jDgood.getJdPrice()));
//            }
            if (good.getAllCnt() == null) good.setAllCnt(jDgood.getTotalCount());
            if (good.getPlatform() == null) good.setPlatform("JD");
            if (good.getType() == null) good.setType(jDgood.getType());
            if (good.getBrand() == null) good.setBrand(jDgood.getBrand());
            goodRepository.save(good);
        }

        List<JDgood> jDgoods = resultItems.get("wareList");
        if (jDgoods != null) {
            jDgoodRepository.save(jDgoods);
        }

    }
}
