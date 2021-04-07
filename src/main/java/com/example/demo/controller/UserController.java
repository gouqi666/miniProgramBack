package com.example.demo.controller;

import com.example.demo.model.Patient;
import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;
import com.example.demo.token.UserLoginToken;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @ResponseBody
    @UserLoginToken
    @PostMapping("/addPatient")
    @ApiOperation(value = "添加就诊人")
    public Patient addPatient(Patient patient) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        User user = userService.findByOpenId(request.getAttribute("open_id").toString()).getModule();
        patient.setUser(user);
        return userService.addPatient(patient);
    }
    public List<Patient> findPatientByUser (User user) {
        return userService.findPatientByUser(user);
    }

}
