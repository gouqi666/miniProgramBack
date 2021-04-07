package com.example.demo.controller;

import ch.qos.logback.core.util.FileUtil;
import com.example.demo.model.AuthUserDto;
import com.example.demo.model.Result;
import com.example.demo.model.SliderPic;
import com.example.demo.service.AuthService;
import com.example.demo.service.OssService;
import com.example.demo.token.UserLoginToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Resource
    private OssService ossService;

    @ResponseBody
    @UserLoginToken
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<AuthUserDto> login(@RequestBody AuthUserDto authUserDto, HttpServletRequest request) throws Exception {
        log.info(authUserDto.toString());
         Result<AuthUserDto> result = authService.login(authUserDto, request);
         return result;
    }

    /*@PostMapping(value = "/upload")
    // 上传文件会自动绑定到MultipartFile
    public String upload(HttpServletRequest request,
                         @RequestParam("file") MultipartFile file) throws Exception{
        //如果文件不为空，写入上传路径
        if (!file.isEmpty()){
            //上传文件路径
            String path = request.getServletContext().getRealPath("/upload/");
            System.out.println("path = " + path);
            //上传文件名
            String filename = file.getOriginalFilename();
            File filePath = new File(path, filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filePath.getParentFile().exists()){
                filePath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            file.transferTo(new File(path+File.separator + filename));
            System.out.println(filename);
            return "success";
        }else {
            return "error";
        }
    }*/

    //上传头像的方法
    @PostMapping("/uploadSliderPic")
    @ApiOperation(value = "文件上传")
    public Result<String> uploadOssFile(MultipartFile file) {
        String url = ossService.uploadFileAvatar(file);
        authService.savePic(url);
        Result<String> result = new Result<>();
        return result.ok(url);
    }

    @GetMapping(value = "/allSliderPic", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取所有轮播图图片")
    public Result<List<SliderPic>> getAllSliderPic() {
        Result<List<SliderPic>> result = new Result<>();
        return result.ok(authService.getAllSliderPic());
    }
}
