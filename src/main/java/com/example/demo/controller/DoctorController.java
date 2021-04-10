package com.example.demo.controller;

import com.example.demo.mapper.DoctorMapper;
import com.example.demo.model.Department;
import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.model.Schedule;
import com.example.demo.service.DoctorService;
import com.example.demo.service.OssService;
import com.example.demo.token.UserLoginToken;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/doctor")
@Slf4j
public class DoctorController {


    @Autowired
    OssService ossService;

    @Autowired
    DoctorService doctorService;
    @ResponseBody
    @UserLoginToken
    @PostMapping(value = "/uploadAvatar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "上传医生头像")
    public String uploadDoctorAvatar (@RequestBody MultipartFile file) {
        String url = ossService.uploadFileAvatar(file);
        return url;
    }

    @ResponseBody
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加医生")
    public Doctor save (@RequestBody Doctor doctor) {

         doctorService.save(doctor);
         return doctor;
    }

    @ResponseBody
    @GetMapping(value = "/findAllDoctors", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询所有医生")
    public List<Doctor> findAllDoctors () {
        return doctorService.findAllDoctors();
    }


    @ResponseBody
    @GetMapping(value = "/findAllDepartment", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询所有科室")
    public List<Department> findAllDepartment(){
        return doctorService.findAllDepartment();
    }


    @ResponseBody
    @PostMapping(value = "/findDcotorsByDepartment", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询对应科室下的医生")
    public List<Doctor> findDcotorsByDepartment(@RequestBody Department department){
        return doctorService.findDoctorsByDepartmentId(department.getId().toString());
    }

    @ResponseBody
    @PostMapping(value = "/findDcotorsByDepartmentAndDate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询对应科室下的医生")
    public List<Doctor> findDcotorsByDepartmentAndDate(@RequestBody Map param){ // date指星期几
        return doctorService.findDoctorsByDepartmentIdAndDate(param.get("id").toString(), param.get("date").toString());
    }
}
