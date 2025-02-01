package com.mservice.msg_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mservice.msg_web.service.DaehoSignUpService;

/**
 * @author daeho.chang
 */
@RestController
@RequestMapping("/test/daeho")
public class DaehoSignUpController {

	@Autowired
	private DaehoSignUpService daehoSignUpService;

	@PostMapping("/signup")
	public ResponseEntity<Void> signUp(@RequestParam String userId, @RequestParam String password) {
		daehoSignUpService.signUp(userId, password);
		return ResponseEntity.ok().build();
	}
}