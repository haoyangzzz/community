package com.yh.mapper;

import com.yh.community.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void  insert(User user);

    @Select("select * from user where token = #{token}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "account_id",property = "accountId"),
            @Result(column = "token",property = "token"),
            @Result(column = "gmt_create",property = "gmtCreate"),
            @Result(column = "gmt_modified",property = "gmtModified"),
            @Result(column = "bio",property = "bio"),
            @Result(column = "avatar_url",property = "avatarUrl")
    })
    User findByToken(@Param("token") String token);//如果是一个类的话会自动匹配放入 否则需要加@param注解



    @Select("select * from user where id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "account_id",property = "accountId"),
            @Result(column = "token",property = "token"),
            @Result(column = "gmt_create",property = "gmtCreate"),
            @Result(column = "gmt_modified",property = "gmtModified"),
            @Result(column = "bio",property = "bio"),
            @Result(column = "avatar_url",property = "avatarUrl")
    })
    User findById(@Param("id") Integer id);


}
