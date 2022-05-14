package com.hsy.creatorservice.controller;


import com.hsy.commonutil.result.Result;
import com.hsy.creatorservice.entity.subject.OneSubject;
import com.hsy.creatorservice.service.FoxSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 视频类别1 前端控制器
 * </p>
 *
 * @author hsy
 * @since 2022-05-03
 */
@Api(description="课程分类管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/creatorservice/subject")
public class FoxSubjectController {

    //获取上传过来文件，把文件内容读取出来
    @Autowired
    public FoxSubjectService subjectService;
    @PostMapping("addSubject")
    public Result addSubject(MultipartFile file) {
        //上传过来excel文件
        subjectService.saveSubject(file,subjectService);
        return Result.ok();
    }


    @GetMapping("getAllSubject")
    public Result getAllSubject() {
        //list集合泛型是一级分类
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return Result.ok(list);
    }

}

