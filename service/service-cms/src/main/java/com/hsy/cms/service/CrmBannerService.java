package com.hsy.cms.service;

import com.hsy.cms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author hsy
 * @since 2022-05-14
 */
public interface CrmBannerService extends IService<CrmBanner> {
    //查询所有banner
    List<CrmBanner> selectAllBanner();
}
