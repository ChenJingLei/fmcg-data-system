package com.john.cloud.provider.repositories;

import com.john.cloud.provider.model.Good;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by cjl20 on 2017/5/16.
 */
public interface GoodRepository extends JpaRepository<Good, Long> {

    @Cacheable
    Good findByGoodUrl(String goodUrl);

}
