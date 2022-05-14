package com.hsy.creatorservice.service;

import com.hsy.creatorservice.entity.FoxCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hsy.creatorservice.entity.vo.CourseInfoVo;

/**
 * <p>
 * 创作者视频 服务类
 * </p>
 *
 * @author hsy
 * @since 2022-05-05
 */
public interface FoxCourseService extends IService<FoxCourse> {


    void updateCourseInfo(CourseInfoVo courseInfoVo);

    //添加课程基本信息的方法
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);
}
