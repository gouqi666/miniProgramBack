package com.example.demo.mapper;

import com.example.demo.model.Patient;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PatientMapper {
    @Insert("insert into patient (name,id_number,phone_number,user_id) values" +
            " (#{name},#{idNumber},#{phoneNumber},#{userId})")
    @ResultType(Patient.class)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Patient save(Patient patient);

    @Select("select * from patient where user_id = #{userId}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "create_time", property = "createTime"),
            // map-underscore-to-camel-case = true 可以实现一样的效果
            // @Result(column = "update_time", property = "updateTime"),
    })
    @ResultType(Patient.class)
    User findPatientByUser(User user);
}
