package com.hsy.creatorservice.service;

import com.hsy.creatorservice.entity.FoxVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 视频 服务类
 * </p>
 *
 * @author hsy
 * @since 2022-05-05
 */
public interface FoxVideoService extends IService<FoxVideo> {

public boolean removeByCourseId(String id);
}
