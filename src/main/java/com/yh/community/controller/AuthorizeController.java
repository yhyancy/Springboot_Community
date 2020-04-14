package com.yh.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
	@GetMapping("/callback")
	public String callack(@RequestParam(name = "code" )String code, @RequestParam(name = "state") String state) {
		return "index";
	}
}
