package com.yh.mapper;

import com.yh.community.model.Question;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Component
public interface QuestionMapper {

    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);


    @Select("select * from question limit #{offset},#{size}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "description",property = "description"),
            @Result(column = "gmt_create",property = "gmtCreate"),
            @Result(column = "gmt_modified",property = "gmtModified"),
            @Result(column = "creator",property = "creator"),
            @Result(column = "view_count",property = "viewCount"),
            @Result(column = "comment_count",property = "commentCount"),
            @Result(column = "like_count",property = "likeCount"),
            @Result(column = "creator",property = "user",one = @One(select = "com.yh.mapper.UserMapper.findById",fetchType = FetchType.EAGER))
    })
    List<Question> list(@Param("offset") Integer offset,@Param("size") Integer size);

    @Select("select * from question where creator = #{userId} limit #{offset},#{size}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "description",property = "description"),
            @Result(column = "gmt_create",property = "gmtCreate"),
            @Result(column = "gmt_modified",property = "gmtModified"),
            @Result(column = "creator",property = "creator"),
            @Result(column = "view_count",property = "viewCount"),
            @Result(column = "comment_count",property = "commentCount"),
            @Result(column = "like_count",property = "likeCount"),
            @Result(column = "creator",property = "user",one = @One(select = "com.yh.mapper.UserMapper.findById",fetchType = FetchType.EAGER))
    })
    List<Question> listByUserId(@Param("userId") Integer userId ,@Param("offset") Integer offset,@Param("size") Integer size);

    @Select("select count(*) from question")
    Integer total();

    @Select("select * from question where id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "description",property = "description"),
            @Result(column = "gmt_create",property = "gmtCreate"),
            @Result(column = "gmt_modified",property = "gmtModified"),
            @Result(column = "creator",property = "creator"),
            @Result(column = "view_count",property = "viewCount"),
            @Result(column = "comment_count",property = "commentCount"),
            @Result(column = "like_count",property = "likeCount"),
            @Result(column = "creator",property = "user",one = @One(select = "com.yh.mapper.UserMapper.findById",fetchType = FetchType.EAGER))
    })
    Question getById(@Param("id") Integer id);
}
