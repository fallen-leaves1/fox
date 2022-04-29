package com.hsy.creatorservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsy.creatorservice.entity.FoxCreator;
import com.hsy.creatorservice.mapper.FoxCreatorMapper;
import com.hsy.creatorservice.service.FoxCreatorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 创作者 服务实现类
 * </p>
 *
 * @author hsy
 * @since 2022-04-29
 */
@Service
public class FoxCreatorServiceImpl extends ServiceImpl<FoxCreatorMapper, FoxCreator> implements FoxCreatorService {

    @Resource
    FoxCreatorMapper foxCreatorMapper;
    @Override
    public Page<FoxCreator> getCreatortFrontList(Page<FoxCreator> pageParam) {
        QueryWrapper<FoxCreator> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        foxCreatorMapper.selectPage(pageParam,queryWrapper);
        return pageParam;
    }
}
