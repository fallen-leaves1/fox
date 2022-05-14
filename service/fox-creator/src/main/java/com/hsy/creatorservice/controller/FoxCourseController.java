package com.hsy.creatorservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsy.commonutil.result.Result;
import com.hsy.creatorservice.entity.FoxCourse;
import com.hsy.creatorservice.entity.FoxCreator;
import com.hsy.creatorservice.entity.vo.CourseInfoVo;
import com.hsy.creatorservice.entity.vo.CourseQuery;
import com.hsy.creatorservice.entity.vo.CreatorQuery;
import com.hsy.creatorservice.service.FoxCourseService;
import com.hsy.creatorservice.service.FoxVideoService;
import com.hsy.servicebase.exception.GuliException;
import com.sun.xml.bind.v2.model.core.ID;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 创作者视频 前端控制器
 * </p>
 *
 * @author hsy
 * @since 2022-05-05
 */
@RestController
@RequestMapping("/creatorservice/course")
@CrossOrigin
public class FoxCourseController {
    @Autowired
    private FoxCourseService foxCourseService;
    @Resource
    private FoxVideoService foxVideoService;

    @PostMapping("addCourseInfo")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = foxCourseService.saveCourseInfo(courseInfoVo);
        return Result.ok(id);
    }
    @GetMapping("getCourseInfo/{courseId}")
    public Result getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = foxCourseService.getCourseInfo(courseId);
        return Result.ok(courseInfoVo);
    }
    @PostMapping("updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        foxCourseService.updateCourseInfo(courseInfoVo);
        return Result.ok();
    }
    //删除视频
    @DeleteMapping("{courseId}")
    public Result deleteCourse(@PathVariable String courseId) {
        try {
            foxCourseService.removeById(courseId);
            boolean flag = foxVideoService.removeByCourseId(courseId);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(201,"删除课程信息失败");
        }
    }
    //课程最终发布
    //修改课程状态
    @GetMapping("publishCourse/{id}")
    public Result publishCourse(@PathVariable String id) {
        FoxCourse foxCourse =new FoxCourse();
        foxCourse.setId(id);
        foxCourse.setStatus("Normal");
        foxCourseService.updateById(foxCourse);
        return Result.ok();
    }
    @ApiOperation(value = "分页条件查询视频")
    @PostMapping("pageCreatorCondition/{current}/{limit}")
    public Result pageTeacherCondition(@PathVariable long current, @PathVariable long limit,
                                       @RequestBody(required = false)CourseQuery courseQuery) {
        //创建page对象
        Page<FoxCourse> coursePage = new Page<>(current,limit);

        //构建条件
        QueryWrapper<FoxCourse> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String name = courseQuery.getTitle();
        String subjectParentId=courseQuery.getSubjectParentId();
        String subjectId=courseQuery.getSubjectId();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("title",name);
        }
        if(!StringUtils.isEmpty(subjectParentId)) {
            wrapper.eq("subject_parent_id",subjectParentId);
        }
        if(!StringUtils.isEmpty(subjectId)) {
            wrapper.ge("subject_id",subjectId);
        }
        wrapper.orderByDesc("gmt_create");
        //调用方法实现条件查询分页
        foxCourseService.page(coursePage,wrapper);


        return Result.ok(coursePage);
    }
}

