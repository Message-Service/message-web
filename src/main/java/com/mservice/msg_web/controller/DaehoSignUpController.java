/**
 * DaehoSigninController  2025. 1. 31.
 *
 * Copyright 2025 NAVER Corp.
 * All rights reserved.
 *
 * Except in the case of internal use for NAVER,
 * unauthorized use or redistribution of this software are strongly prohibited.
 */
package com.mservice.msg_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mservice.msg_web.service.DaehoSignUpService;

/**
 * @author daeho.chang
 */
@Controller
@RestController("test/daeho")
public class DaehoSignUpController {

	@Autowired
	private DaehoSignUpService daehoSignUpService;

	@PostMapping("/signup")
	public ResponseEntity<Void> signUp(@RequestParam String id, @RequestParam String password) {
		daehoSignUpService.signUp(id, password);
		return ResponseEntity.ok().build();
	}
}