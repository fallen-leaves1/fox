package com.hsy.creatorservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsy.commonutil.result.Result;
import com.hsy.creatorservice.client.VodClient;
import com.hsy.creatorservice.entity.FoxVideo;
import com.hsy.creatorservice.mapper.FoxVideoMapper;
import com.hsy.creatorservice.service.FoxVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsy.servicebase.exception.GuliException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * <p>
 * 视频 服务实现类
 * </p>
 *
 * @author hsy
 * @since 2022-05-05
 */
@Service
public class FoxVideoServiceImpl extends ServiceImpl<FoxVideoMapper, FoxVideo> implements FoxVideoService {
    @Resource
    private VodClient vodClient;

    //@Override
    public boolean removeByCourseId(String courseId) {
        QueryWrapper<FoxVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        FoxVideo foxVideo = baseMapper.selectOne(queryWrapper);
        String videoSourceId = foxVideo.getVideoSourceId();
        //判断小节里面是否有视频id
        if (!StringUtils.isEmpty(videoSourceId)) {
            //根据视频id，远程调用实现视频删除
            Result result = vodClient.removeAlyVideo(videoSourceId);
            if (result.getCode() == 201) {
                throw new GuliException(201, "删除视频失败，熔断器...");
            }
        }
            int flag = baseMapper.delete(queryWrapper);
            if (flag != 0) {
                return true;
            } else {
                return false;
            }
        }

}

