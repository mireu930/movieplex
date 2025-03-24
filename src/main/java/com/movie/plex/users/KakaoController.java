package com.movie.plex.users;

import java.util.Map;

import javax.servlet.http.HttpSession;

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
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login/oauth2/code/kakao", method = RequestMethod.GET)
	public String kakaoLogin(@RequestParam String code, Model model, HttpSession session) throws Exception{
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
	    
	    
	    
	    UserDTO userDTO = userService.findEmail(email);
	    
	    if(userDTO!=null) {
	    	userDTO = userService.getLogin(userDTO);
	    	session.setAttribute("accessToken", accessToken);
	    	session.setAttribute("userEmail", email);
	    	session.setAttribute("userName", nickname);
	    	
	    	session.setAttribute("user", userDTO);
	    	
	    	return "redirect:/";
	    } else {
	    	model.addAttribute("name", nickname);
	    	model.addAttribute("email", email);
	    	return "users/kakaoJoin";	    	
	    }

	}
	
	@RequestMapping(value = "/login/oauth2/code/kakao",method = RequestMethod.POST)
	public String kakaoLogin(UserDTO userDTO, HttpSession session, Model model) throws Exception {
		int result = userService.kakaoJoin(userDTO);
		
		model.addAttribute("result", "회원가입성공");
		model.addAttribute("path", "/");
		
		return "commons/result";
	}
}
