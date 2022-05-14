package com.hsy.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsy.cms.entity.CrmBanner;
import com.hsy.cms.service.CrmBannerService;
import com.hsy.commonutil.result.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 首页banner表 前端后台控制器
 * </p>
 *
 * @author hsy
 * @since 2022-05-14
 */
@RestController
@RequestMapping("/cms/crmbanner")
@CrossOrigin
public class CrmBannerController {
    @Autowired
    private CrmBannerService bannerService;

    //1 分页查询banner
    @GetMapping("pageBanner/{page}/{limit}")
    public Result pageBanner(@PathVariable long page,@PathVariable long limit) {
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
        bannerService.page(pageBanner,null);
        return Result.ok(pageBanner);
    }

    //2 添加banner
    @PostMapping("addBanner")
    public Result addBanner(@RequestBody CrmBanner crmBanner) {
        bannerService.save(crmBanner);
        return Result.ok();
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public Result get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return Result.ok(banner);
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public Result updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return Result.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        bannerService.removeById(id);
        return Result.ok();
    }


}

