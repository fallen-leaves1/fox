package com.hsy.creatorservice.client;

import com.hsy.commonutil.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-vod" ,fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {
//路径要写绝对的
//有@pathvariable要指定参数名称
    @DeleteMapping("/vodservice/video/removeAlyVideo/{id}")
    public Result removeAlyVideo(@PathVariable("id") String id);
}
