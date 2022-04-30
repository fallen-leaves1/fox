package com.hsy.creatorservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsy.commonutil.result.Result;
import com.hsy.creatorservice.entity.FoxCreator;
import com.hsy.creatorservice.entity.vo.CreatorQuery;
import com.hsy.creatorservice.service.FoxCreatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 创作者 前端控制器
 * </p>
 *
 * @author hsy
 * @since 2022-04-29
 */
@RestController
@RequestMapping("/creatorservice/creator")
@Api(value = "创作者列表查看接口")
public class FoxCreatorController {
    @Autowired
    private FoxCreatorService foxCreatorService;

    @ApiOperation(value = "所有创作者列表")
    @GetMapping("findAll")
    public Result findAllCreator() {
        //调用service的方法实现查询所有的操作
        List<FoxCreator> list = foxCreatorService.list(null);
        return Result.ok(list);
    }

    @ApiOperation(value = "创作者逻辑删除")
    @DeleteMapping("{id}")
    public Result removeCreator(@ApiParam(name = "id", value = "讲师ID", required = true)
                                    @PathVariable String id){
        boolean flag = foxCreatorService.removeById(id);
        if (flag){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "添加创作者")
    @PostMapping("addCreator")
    public Result addCreator(@RequestBody FoxCreator foxCreator){
        boolean flag = foxCreatorService.save(foxCreator);
        if (flag){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
    //根据讲师id进行查询
    @ApiOperation(value = "id查询创作者")
    @GetMapping("getCreator/{id}")
    public Result getCreator(@PathVariable String id) {
        FoxCreator foxCreator = foxCreatorService.getById(id);
        return Result.ok(foxCreator);
    }

    //讲师修改功能
    @ApiOperation(value = "更新创作者")
    @PostMapping("updateCreator")
    public Result updateCreator(@RequestBody  FoxCreator foxCreator) {
        boolean flag = foxCreatorService.updateById(foxCreator);
        if(flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "分页条件查询创作者")
    @PostMapping("pageCreatorCondition/{current}/{limit}")
    public Result pageTeacherCondition(@PathVariable long current,@PathVariable long limit,
                                  @RequestBody(required = false) CreatorQuery creatorQuery) {
        //创建page对象
        Page<FoxCreator> creatorPage = new Page<>(current,limit);

        //构建条件
        QueryWrapper<FoxCreator> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String name = creatorQuery.getName();
        Integer level = creatorQuery.getLevel();
        String begin = creatorQuery.getBegin();
        String end = creatorQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }
        int i = 10/0;
        //排序
        wrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询分页
        foxCreatorService.page(creatorPage,wrapper);


        return Result.ok(creatorPage);
    }

//    @ApiOperation(value = "分页条件查询创作者")
//    @PostMapping("updateCreator")
//    public Result updateCreator(@RequestBody  FoxCreator foxCreator) {
//        boolean flag = foxCreatorService.updateById(foxCreator);
//        if(flag) {
//            return Result.ok();
//        } else {
//            return Result.fail();
//        }
//    }
}

