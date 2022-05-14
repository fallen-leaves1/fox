package com.hsy.creatorservice.client;

import com.hsy.commonutil.result.Result;
import org.springframework.stereotype.Component;

@Component
public class VodFileDegradeFeignClient implements VodClient{

    @Override
    public Result removeAlyVideo(String id) {
        return Result.fail("删除视频出错");
    }
}
