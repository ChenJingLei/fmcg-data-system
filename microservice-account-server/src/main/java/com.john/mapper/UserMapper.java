package com.john.mapper;

import com.john.model.User;
import org.apache.ibatis.annotations.*;

/**
 * Created by cjl20 on 6/4/2017.
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE USERNAME = #{username} and Password=#{password}")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Select("SELECT * FROM user WHERE email = #{email}")
    User findByEmail(@Param("email") String email);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);

    @Insert("INSERT INTO user(firstname,lastname,username,password,email,phone,company,enable) VALUES(#{firstname},#{lastname},#{username},#{password},#{email},#{phone},#{company},#{enable})")
    int insertByUser(User user);

    @Update("UPDATE user SET firstname=#{firstname},lastname=#{lastname},username=#{username},password=#{password},email=#{email},phone=#{phone},company=#{company},enable=#{enable} WHERE id=#{id}")
    void update(User user);
}
