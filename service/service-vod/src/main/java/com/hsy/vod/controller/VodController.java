package com.hsy.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.hsy.commonutil.result.Result;
import com.hsy.servicebase.exception.GuliException;
import com.hsy.vod.service.VodService;
import com.hsy.vod.utils.ConstantVodUtils;
import com.hsy.vod.utils.InitVodCilent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/vodservice/video")
@CrossOrigin
public class VodController {
    @Autowired
    private VodService vodService;

    @PostMapping("uploadVideo")
    public Result uploadvideo(MultipartFile file){
        String videoId = vodService.uploadVideoAly(file);
        return Result.ok(videoId);
    }

    //根据视频id删除阿里云视频
    @DeleteMapping("removeAlyVideo/{id}")
    public Result removeAlyVideo(@PathVariable String id) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return Result.ok();
        }catch(Exception e) {
            e.printStackTrace();
            throw new GuliException(201,"删除视频失败");
        }
    }

}
