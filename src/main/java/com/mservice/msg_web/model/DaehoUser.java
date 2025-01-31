/**
 * DaehoUser  2025. 1. 31.
 *
 * Copyright 2025 NAVER Corp.
 * All rights reserved.
 *
 * Except in the case of internal use for NAVER,
 * unauthorized use or redistribution of this software are strongly prohibited.
 */
package com.mservice.msg_web.model;

/**
 * @author daeho.chang
 */
public class DaehoUser {
	private long id;
	private String userId;
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}