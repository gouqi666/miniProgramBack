package com.example.demo.mapper;

import com.example.demo.model.SliderPic;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SliderPicMapper {
    @Insert("insert into SliderPic (pic_url) values (#{url})")
    @ResultType(Long.class)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Long save(@Param("url") String url);

    @Select("select * from SliderPic")
    @ResultType(SliderPic.class)
    List<SliderPic> getAllSliderPic();

}

