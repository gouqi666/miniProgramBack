package com.example.demo.mapper;

import com.example.demo.model.Department;
import com.example.demo.model.Doctor;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface DoctorMapper {
    @Insert("insert into doctor (name,avatar_url,description,id_number,phone_number,address,total_patients_num,title,department_id) values" +
            " (#{name},#{avatarUrl},#{description},#{idNumber},#{phoneNumber},#{address},#{totalPatientsNum},#{title},#{department.id})")
    @ResultType(Long.class)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Long save(Doctor doctor);


    @Select("select * from doctor")
    @ResultType(Doctor.class)
    List<Doctor> findAllDoctors();

    @Insert({
            "<script>" +
                    "insert into doctor_schedule(doctor_id,schedule_id) values" +
                    "<foreach item = 'item' index = 'index' collection = 'list'  separator=','>" + // open='(' 和close=')'不应该加进去，不然会报错，括号不匹配，（）
                    "(#{item.doctor_id}, #{item.schedule_id})" +
                    "</foreach>"+
            "</script>"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Long saveDoctorSchedule(@Param("list") List<Map<String,Object>> paraList);

    @Select({
            "<script>" +
                    "select  id from schedule where week_day in " +
                    "<foreach item = 'item' index = 'index' collection = 'week_days' open='(' close=')' separator=',' >" +
                    "#{item}" +
                    "</foreach>"+
            "</script>"})
    @ResultType(Long.class)
    List<Long> findIdByWeekDay(@Param("week_days") List<Integer> weekdays);


    @Select("select * from department")
    @ResultType(Department.class)
    List<Department> findAllDepartment();

    @Select("select * from doctor where department_id = #{id}")
    @ResultType(Doctor.class)
    List<Doctor> findDoctorsByDepartmentId (@Param("id")String id);

    @Select({
            "<script>" +
            "SELECT * FROM schedule" +
            "  JOIN doctor_schedule ON schedule.id = doctor_schedule.schedule_id" +
            "  JOIN doctor ON doctor_schedule.doctor_id = doctor.id" +
            "  where schedule.week_day in" +
            "<foreach item = 'item' index = 'index'  collection = 'dates' open='(' close=')' separator=',' >" +
            "#{item}" +
            "</foreach>"+
            "and department_id = #{id}" +
            "</script>"
        })
    @ResultType(Doctor.class)
    List<Doctor> findDoctorsByDepartmentIdAndDate ( @Param("id") String id, @Param("dates") List<Long> dates);

}