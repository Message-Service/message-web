/**
 * DaehoUserMapper  2025. 1. 31.
 *
 * Copyright 2025 NAVER Corp.
 * All rights reserved.
 *
 * Except in the case of internal use for NAVER,
 * unauthorized use or redistribution of this software are strongly prohibited.
 */
package com.mservice.msg_web.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.mservice.msg_web.model.DaehoUser;

/**
 * @author daeho.chang
 */
@Mapper
public interface DaehoUserMapper {
	DaehoUser selectUserById(long id);
	void insert(DaehoUser user);
}