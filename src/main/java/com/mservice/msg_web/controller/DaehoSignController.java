package com.mservice.msg_web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mservice.msg_web.controller.model.Authentication;
import com.mservice.msg_web.service.DaehoSignService;

/**
 * @author daeho.chang
 */
@RestController
@RequestMapping("/test/daeho")
public class DaehoSignController {
	private static final int ACCESS_EXPIRE_SECONDS = 60;
	private static final int REFRESH_EXPIRE_SECONDS = 60 * 60;
	@Value("${jwt.secret.key}")
	private String jwtSecretKey;

	@Autowired
	private DaehoSignService daehoSignService;

	@PostMapping("/signup")
	public ResponseEntity<Void> signUp(@RequestParam String userId, @RequestParam String password) {
		daehoSignService.signUp(userId, password);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/signin")
	public ResponseEntity<Authentication> signIn(@RequestParam String userId, @RequestParam String password) {
		var user = daehoSignService.signIn(userId, password);
		var issuedDate = new Date();
		var accessToken = JWT.create()
			.withSubject(userId)
			.withIssuedAt(issuedDate)
			.withExpiresAt(issuedDate.toInstant().plusSeconds(ACCESS_EXPIRE_SECONDS))
			.sign(Algorithm.HMAC256(jwtSecretKey));
		var refreshToken = JWT.create()
			.withSubject(userId)
			.withIssuedAt(issuedDate)
			.withExpiresAt(issuedDate.toInstant().plusSeconds(REFRESH_EXPIRE_SECONDS))
			.sign(Algorithm.HMAC256(jwtSecretKey));
		var authentication = new Authentication(user.getUserId(), accessToken, refreshToken);
		return ResponseEntity.ok(authentication);
	}
}