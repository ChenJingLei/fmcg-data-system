package com.example.demo.mapper;

import com.example.demo.model.Good;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by cjl20 on 6/5/2017.
 */
@Mapper
public interface UserGoodMapper {

    //    @Results({
//            @Result(property = "user.id", column = "uid"),
//            @Result(property = "user.firstname", column = "firstname"),
//            @Result(property = "user.lastname", column = "lastname"),
//            @Result(property = "user.username", column = "username"),
//            @Result(property = "user.password", column = "password"),
//            @Result(property = "user.email", column = "email"),
//            @Result(property = "user.phone", column = "phone"),
//            @Result(property = "user.company", column = "company"),
//            @Result(property = "user.enable", column = "enable"),
//            @Result(property = "authority.id", column = "aid"),
//    })
    @Select("Select * from good,user_good where good.id = user_good.gid and uid=#{uid}")
    public List<Good> findAllByUid(Long uid);

    @Delete("delete from user_good where gid=#{0} and uid=#{1}")
    public void deleteByGidAndUid(Long gid, Long uid);

}
