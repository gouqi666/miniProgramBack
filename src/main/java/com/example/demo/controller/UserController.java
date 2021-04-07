package com.example.demo.controller;

import com.example.demo.model.Patient;
import com.example.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addPatient")
    @ApiOperation(value = "添加就诊人")
    public Patient addPatient(Patient patient) {
        return userService.addPatient(patient);
    }


}
