package com.yh.community.controller;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yh.community.Provider.GithubProvider;
import com.yh.community.dto.AccessTokenDTO;
import com.yh.community.dto.GithubUser;
import com.yh.community.mapper.UserMapper;
import com.yh.community.model.User;

import okhttp3.Response;

@Controller
public class AuthorizeController {
	@Autowired
	private GithubProvider githubProvider;
	@Autowired
	private UserMapper userMapper;

	@Value("${github.client.id}")
	private String clientId;
	@Value("${github.client.secret}")
	private String clientSecret;
	@Value("${github.redirect.uri}")
	private String redirectUri;

	@GetMapping("/callback")
	public String callack(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state,
			HttpServletRequest request, HttpServletResponse response) {
		AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
		accessTokenDTO.setClient_id(clientId);
		accessTokenDTO.setClient_secret(clientSecret);
		accessTokenDTO.setCode(code);
		accessTokenDTO.setRedirect_uri(redirectUri);
		accessTokenDTO.setState(state);
		String accessToken = githubProvider.getAccessToken(accessTokenDTO);
		GithubUser githubUser = githubProvider.getUser(accessToken);
		System.out.println(githubUser.getName());
		System.out.println(githubUser);
		if (githubUser != null) {
			User user= new User();	
			String token= UUID.randomUUID().toString();
			user.setToken(token);
			user.setName(githubUser.getName());
			user.setAccountId(String.valueOf(githubUser.getId()));
			user.setGmtCreate(System.currentTimeMillis());
			user.setGmtModified(user.getGmtCreate());
			userMapper.insert(user);
			//手动写cookie
			response.addCookie(new Cookie("token", token));
			
//			request.getSession().setAttribute("user", githubUser);
			return "redirect:/";
		} else {
			return "redirect:/";
		}

	}
}
