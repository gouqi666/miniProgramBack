package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into user (open_id,nick_name,gender,avatar_url,union_id,country,province,city,language,email,phone,remarks,enabled) values" +
            " (#{openId}, #{nickName},#{gender},#{avatarUrl},#{unionId},#{country},#{province},#{city},#{language},#{email},#{phone},#{remarks},#{enabled})")
    @ResultType(User.class)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    User save(User user);

    @Select("select * from user where open_id = #{openid}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "create_time", property = "createTime"),
            // map-underscore-to-camel-case = true 可以实现一样的效果
            // @Result(column = "update_time", property = "updateTime"),
    })
    @ResultType(User.class)
     User findByOpenId(@Param("openid") String openid);

}
