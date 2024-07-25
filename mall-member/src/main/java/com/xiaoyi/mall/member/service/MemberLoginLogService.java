package com.xiaoyi.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyi.mall.common.utils.PageInfo;
import com.xiaoyi.mall.member.entity.MemberLoginLogEntity;

import java.util.Map;

/**
 * 会员登录记录
 *
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-13 22:52:27
 */
public interface MemberLoginLogService extends IService<MemberLoginLogEntity> {

    PageInfo queryPage(Map<String, Object> params);
}

