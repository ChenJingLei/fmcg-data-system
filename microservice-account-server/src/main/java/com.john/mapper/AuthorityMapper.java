package com.john.mapper;

import com.john.model.Authority;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by cjl20 on 6/4/2017.
 */

@Mapper
public interface AuthorityMapper {

    @Select("SELECT * FROM authorities WHERE id = #{id}")
    List<Authority> findByUsername(@Param("id") Long id);

    @Insert("INSERT INTO authorities VALUES(#{id},#{authority})")
    int insertByAuthority(Authority authority);

    @Update("UPDATE authorities SET authority=#{authority} WHERE id=#{id}")
    void update(Authority authority);

    @Delete("DELETE FROM authorities WHERE id =#{id}")
    void delete(Authority authority);
}
