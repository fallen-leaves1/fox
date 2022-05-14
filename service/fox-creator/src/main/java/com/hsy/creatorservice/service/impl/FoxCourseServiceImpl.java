package com.hsy.creatorservice.service.impl;

import com.hsy.creatorservice.entity.FoxCourse;
import com.hsy.creatorservice.entity.FoxCourseDescription;
import com.hsy.creatorservice.entity.vo.CourseInfoVo;
import com.hsy.creatorservice.mapper.FoxCourseMapper;
import com.hsy.creatorservice.service.FoxCourseDescriptionService;
import com.hsy.creatorservice.service.FoxCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsy.servicebase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 创作者视频 服务实现类
 * </p>
 *
 * @author hsy
 * @since 2022-05-05
 */
@Service
public class FoxCourseServiceImpl extends ServiceImpl<FoxCourseMapper, FoxCourse> implements FoxCourseService {

    @Resource
    private FoxCourseDescriptionService courseDescriptionService;


    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {

        FoxCourse Course = new FoxCourse();
        BeanUtils.copyProperties(courseInfoVo,Course);
        int update = baseMapper.updateById(Course);
        if(update == 0) {
            throw new GuliException(201,"修改课程信息失败");
        }
        FoxCourseDescription description = new FoxCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(description);

    }

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        FoxCourse foxCourse =new FoxCourse();
        BeanUtils.copyProperties(courseInfoVo,foxCourse);
        int flag = baseMapper.insert(foxCourse);
        if(flag == 0){
            throw new GuliException(201,"添加课程信息失败");
        }
        //获取添加之后课程id
        String cid = foxCourse.getId();

        //2 向课程简介表添加课程简介
        //edu_course_description
        FoxCourseDescription courseDescription = new FoxCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        //设置描述id就是课程id
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);
        return cid;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //1 查询课程表
        FoxCourse Course = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(Course,courseInfoVo);

        //2 查询描述表
        FoxCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }


}
