package com.hsy.cms.controller;

import com.hsy.cms.entity.CrmBanner;
import com.hsy.cms.service.CrmBannerService;
import com.hsy.commonutil.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//前台使用的banner
@RestController
@RequestMapping("/cms/banner")
@Api(description = "网站首页Banner列表")
@CrossOrigin
public class BannerController {
    @Autowired
    private CrmBannerService bannerService;

        @Cacheable(key = "'selectIndextList'",value = "banner")
        //查询所有banner
        @GetMapping("getAllBanner")
        public Result getAllBanner() {
            List<CrmBanner> list = bannerService.selectAllBanner();
            return Result.ok(list);
        }

}
