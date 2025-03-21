package com.movie.plex.users;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KakaoController {
	
	@Autowired
	 private KakaoApi kakaoApi;

	@RequestMapping(value = "/login/oauth2/code/kakao", method = RequestMethod.GET)
	public String kakaoLogin(@RequestParam String code, Model model){
		System.out.println("kakaologin");
	    // 1. 인가 코드 받기 (@RequestParam String code)

	    // 2. 토큰 받기
	    String accessToken = kakaoApi.getAccessToken(code);

	    // 3. 사용자 정보 받기
	    Map<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);

	    String email = (String)userInfo.get("email");
	    String nickname = (String)userInfo.get("nickname");

	    System.out.println("email = " + email);
	    System.out.println("nickname = " + nickname);
	    System.out.println("accessToken = " + accessToken);
	    
	    model.addAttribute("name", nickname);
	    model.addAttribute("email", email);

	    return "users/join";
	}
}
