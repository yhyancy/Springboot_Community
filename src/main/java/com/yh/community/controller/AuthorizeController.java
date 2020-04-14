package com.yh.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yh.community.Provider.GithubProvider;
import com.yh.community.dto.AccessTokenDTO;

@Controller
public class AuthorizeController {
	@Autowired
	private GithubProvider githubProvider;
	
	@GetMapping("/callback")
	public String callack(@RequestParam(name = "code" )String code, @RequestParam(name = "state") String state) {
		AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
		accessTokenDTO.setClient_id("f2f38818b124625034a1");
		accessTokenDTO.setClient_secret("a31a628c5bb45cec064800fbba56ef3204be37fa");
		accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
		accessTokenDTO.setState(state);
		githubProvider.getAccessToken(accessTokenDTO);
		return "index";
	}
}
