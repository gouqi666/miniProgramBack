package com.example.demo.service;

import com.example.demo.constant.ConstantProperties;
import com.example.demo.converter.WXMappingJackson2HttpMessageConverter;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.LoginResponse;
import com.example.demo.model.Patient;
import com.example.demo.model.Result;
import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    ConstantProperties constantProperties;

    @Autowired
    UserMapper userMapper;

    public Result<User> findByOpenId(String openid) {
        User user= userMapper.findByOpenId(openid);
        Result<User> resultUser =  new Result<>();
        return resultUser.ok(user);
    }

    public User create(User user) {
        return  userMapper.save(user);
    }

    public Long addPatient(Patient patient) {
        return userMapper.addPatient(patient);
    }

    public List<Patient> findPatientByUserId(String user_id) {
        return userMapper.findPatientByUser(user_id);
    }


    public void deletePatientById ( String id) {
        userMapper.deletePatientById(id);
    }
}
