package com.john.mapper;

import com.john.model.UserAuthority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by cjl20 on 6/4/2017.
 */
@Mapper
public interface UserAuthorityMapper {

    @Results({
            @Result(property = "user.id", column = "uid"),
            @Result(property = "user.firstname", column = "firstname"),
            @Result(property = "user.lastname", column = "lastname"),
            @Result(property = "user.username", column = "username"),
            @Result(property = "user.password", column = "password"),
            @Result(property = "user.email", column = "email"),
            @Result(property = "user.phone", column = "phone"),
            @Result(property = "user.company", column = "company"),
            @Result(property = "user.enable", column = "enable"),
            @Result(property = "authority.id", column = "aid"),
            @Result(property = "authority.authority", column = "authority"),
    })
    @Select("select user.id as uid, authorities.id as aid,username,password,firstname,lastname,email,company,phone,enable,authority  from user,authorities where user.id=authorities.id and user.id=#{id}")
    public List<UserAuthority> findById(Long id);

}
