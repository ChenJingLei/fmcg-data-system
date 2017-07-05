package com.example.demo.repositories;

import com.example.demo.model.MallGood;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by cjl20 on 6/9/2017.
 */
public interface MallGoodRepository extends JpaRepository<MallGood, Long> {
}
