package com.mservice.msg_web.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mservice.msg_web.model.DaehoUser;

/**
 * @author daeho.chang
 */
@Mapper
public interface DaehoUserMapper {
	DaehoUser selectUserByUserId(String userId);
	void insert(Map<String, Object> map);
}