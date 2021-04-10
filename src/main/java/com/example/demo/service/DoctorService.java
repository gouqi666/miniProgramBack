package com.example.demo.service;

import com.example.demo.mapper.DoctorMapper;
import com.example.demo.model.Department;
import com.example.demo.model.Doctor;
import com.example.demo.model.Schedule;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    DoctorMapper doctorMapper;

    public Doctor save(Doctor doctor){
        doctorMapper.save(doctor);
        List<Integer> schedules_number= doctor.getSchedules().stream().map(n -> n.getWeekday()).collect(Collectors.toList());
        if (schedules_number != null) {
            List<Long>  schedules_id = doctorMapper.findIdByWeekDay(schedules_number);
            List<Map<String,Object>> paraList = new ArrayList<>();
            for (Long e : schedules_id) {
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("doctor_id",doctor.getId());
                map.put("schedule_id",e);
                paraList.add(map);
            }
            doctorMapper.saveDoctorSchedule(paraList);
        }

        return doctor;
    }

    public List<Doctor> findAllDoctors(){
        return doctorMapper.findAllDoctors();
    }

    public List<Doctor> findDoctorsByDepartmentId(String department_id) {
        return doctorMapper.findDoctorsByDepartmentId(department_id);
    }
    public List<Doctor> findDoctorsByDepartmentIdAndDate(String department_id, String date) {
        List<Long> list = new ArrayList<>();
        Long day = (Long.parseLong(date) * 3) ;
        for(int i = 1; i <= 3; i++) {
            list.add(day);
            day += 1;
        }

        return doctorMapper.findDoctorsByDepartmentIdAndDate(department_id,list);
    }

    public List<Department> findAllDepartment() {
        return doctorMapper.findAllDepartment();
    }

}
