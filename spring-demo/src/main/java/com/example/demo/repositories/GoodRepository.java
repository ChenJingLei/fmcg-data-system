package com.example.demo.repositories;


import com.example.demo.model.Good;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by cjl20 on 2017/5/16.
 */
public interface GoodRepository extends JpaRepository<Good, Long> {

    @Cacheable
    Good findByGoodUrl(String goodUrl);

    List<Good> findAll();
}
