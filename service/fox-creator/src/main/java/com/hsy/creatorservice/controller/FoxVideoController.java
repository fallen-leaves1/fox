package com.hsy.creatorservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsy.commonutil.result.Result;
import com.hsy.creatorservice.client.VodClient;
import com.hsy.creatorservice.entity.FoxCreator;
import com.hsy.creatorservice.entity.FoxVideo;
import com.hsy.creatorservice.service.FoxVideoService;
import com.hsy.servicebase.exception.GuliException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 视频 前端控制器
 * </p>
 *
 * @author hsy
 * @since 2022-05-05
 */
@RestController
@RequestMapping("/creatorservice/video")
@CrossOrigin
public class FoxVideoController {
    @Resource
    private FoxVideoService foxVideoService;
    @Resource
    private VodClient vodClient;

    @PostMapping("addVideo")
    public Result addVideo(@RequestBody FoxVideo foxVideo) {
//        todo 保存时检查时视频资源是否与数据库的相同，如果不同调用删除云端视频再进行保存
        try{
        foxVideoService.save(foxVideo);
        return Result.ok();}catch (Exception e){
            e.printStackTrace();
            throw  new GuliException(201,"添加保存失败，请检查是否重复提交或者数据没有添加完全");
        }
    }
    @DeleteMapping("{id}")
    public Result deleteVideo(@PathVariable String id) {
       boolean flag =foxVideoService.removeByCourseId(id);
        if(flag){
        return Result.ok();
        }
        else {
            return Result.fail();
        }
    }
    @GetMapping("findVideo/{courseId}")
    public Result findVideo(@PathVariable String courseId){
        QueryWrapper<FoxVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        FoxVideo foxVideo =foxVideoService.getOne(queryWrapper);
        return Result.ok(foxVideo);
    }
}

