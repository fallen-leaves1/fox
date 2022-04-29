package com.hsy.creatorservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsy.creatorservice.entity.FoxCreator;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 创作者 服务类
 * </p>
 *
 * @author hsy
 * @since 2022-04-29
 */
public interface FoxCreatorService extends IService<FoxCreator> {
    Page<FoxCreator> getCreatortFrontList(Page<FoxCreator> pageParam);

}
