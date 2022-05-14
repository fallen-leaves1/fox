package com.hsy.creatorservice.service;

import com.hsy.creatorservice.entity.FoxSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hsy.creatorservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 视频类别1 服务类
 * </p>
 *
 * @author hsy
 * @since 2022-05-03
 */
public interface FoxSubjectService extends IService<FoxSubject> {
    //添加课程分类
    void saveSubject(MultipartFile file,FoxSubjectService subjectService);

    //课程分类列表（树形）
    List<OneSubject> getAllOneTwoSubject();

}
