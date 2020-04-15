package com.yh.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yh.community.Provider.GithubProvider;
import com.yh.community.dto.AccessTokenDTO;
import com.yh.community.dto.GithubUser;

@Controller
public class AuthorizeController {
	@Autowired
	private GithubProvider githubProvider;
	
	@Value("${github.client.id}")
	private String clientId;
	@Value("${github.client.secret}")
	private String clientSecret;
	@Value("${github.redirect.uri}")
	private String redirectUri;
	
	@GetMapping("/callback")
	public String callack(@RequestParam(name = "code" )String code, @RequestParam(name = "state") String state) {
		AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
//		accessTokenDTO.setClient_id(clientId);
		accessTokenDTO.setClient_id("f2f38818b124625034a1");
		accessTokenDTO.setClient_secret("a31a628c5bb45cec064800fbba56ef3204be37fa");
		accessTokenDTO.setCode(code);
		accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
		accessTokenDTO.setState(state);
		String accessToken= githubProvider.getAccessToken(accessTokenDTO);
		GithubUser user = githubProvider.getUser(accessToken);
		System.out.println(user.getName());
		return "index";
	}
}
