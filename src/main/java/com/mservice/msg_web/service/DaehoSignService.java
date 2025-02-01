package com.mservice.msg_web.service;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.DateUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mservice.msg_web.mapper.DaehoUserMapper;
import com.mservice.msg_web.model.DaehoUser;

/**
 * @author daeho.chang
 */
@Service
public class DaehoSignService {
	@Autowired
	private DaehoUserMapper daehoUserMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public void signUp(String userId, String password) {
		//1. 중복 검사
		var user = daehoUserMapper.selectUserByUserId(userId);
		if (user != null) {
			//TODO 예외처리 받아서 rest 응답 내려주는 무언가가 필요해보입니다.
			throw new InvalidParameterException("이미 존재하는 아이디입니다. user id : " + userId);
		}
		//2. 비밀번호 암호화
		var encodePassword = passwordEncoder.encode(password);
		//3. DB에 저장
		daehoUserMapper.insert(Map.of("userId", userId, "password", encodePassword));
	}

	@Transactional
	public DaehoUser signIn(String userId, String password) {
		var user = daehoUserMapper.selectUserByUserId(userId);
		if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
			//TODO 예외처리 받아서 rest 응답 내려주는 무언가가 필요해보입니다.
			throw new InvalidParameterException("존재하지 않는 아이디입니다. user id : " + userId);
		}
		return user;
	}
}