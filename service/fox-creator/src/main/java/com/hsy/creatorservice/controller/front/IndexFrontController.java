package com.hsy.creatorservice.controller.front;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsy.commonutil.result.Result;
import com.hsy.creatorservice.entity.FoxCourse;
import com.hsy.creatorservice.entity.FoxCreator;
import com.hsy.creatorservice.service.FoxCourseService;
import com.hsy.creatorservice.service.FoxCreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/creatorservice/indexfront")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private FoxCourseService foxCourseService;

    @Autowired
    private FoxCreatorService foxCreatorService;

    //查询前8条热门视频，查询前4条名up主
    @Cacheable(value = "index",key = "'hostIndex'")
    @GetMapping("index")
    public Result index() {
//      热门视频
        QueryWrapper<FoxCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<FoxCourse> courseList = foxCourseService.list(wrapper);


        QueryWrapper<FoxCreator> wrapperCreator = new QueryWrapper<>();
        wrapperCreator.orderByDesc("level");
        wrapperCreator.last("limit 4");
        List<FoxCreator> creatorList = foxCreatorService.list(wrapperCreator);
       Map<String,Object> MyData = new HashMap<>();
       MyData.put("courseList",courseList);
       MyData.put("creatorList",creatorList);
        return Result.ok(MyData);
    }

}
