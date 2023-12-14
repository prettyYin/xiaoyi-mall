package com.xiaoyi.mall.member.dao;

import com.xiaoyi.mall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author chandler
 * @email 2544212327@qq.com
 * @date 2023-12-13 22:52:26
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
