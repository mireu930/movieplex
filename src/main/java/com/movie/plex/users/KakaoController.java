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
@RequestMapping(value = "/login/oauth2/code/*")
public class KakaoController {
	
	@Autowired
	 private KakaoApi kakaoApi;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "kakao", method = RequestMethod.GET)
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
	    	 if (userDTO.getUserOut() == 1) {
		            // 사용자가 비활성화된 상태일 경우 로그인 실패 처리
		            model.addAttribute("result", "비활성화된 사용자입니다. 관리자에게 문의하세요.");
		            model.addAttribute("path", "/users/login");
		            return "commons/result";  // 비활성화된 사용자 메시지 출력
		        }else {
		        	session.setAttribute("accessToken", accessToken);
		        	session.setAttribute("userEmail", email);
		        	session.setAttribute("userName", nickname);
		        	
		        	session.setAttribute("user", userDTO);
		        	
		        	return "redirect:/";		        	
		        }
	    	
	    } else {
	    	model.addAttribute("name", nickname);
	    	model.addAttribute("email", email);
	    	return "users/kakaoJoin";	    	
	    }

	}
	
	@RequestMapping(value = "kakao",method = RequestMethod.POST)
	public String kakaoLogin(UserDTO userDTO, HttpSession session, Model model) throws Exception {
		int result = userService.kakaoJoin(userDTO);
		
		model.addAttribute("result", "회원가입성공");
		model.addAttribute("path", "/");
		
		return "commons/result";
	}
	
	@RequestMapping(value = "check", method = RequestMethod.GET)
	public String idCheck(UserDTO userDTO, Model model) throws Exception {
		
		userDTO = userService.idCheck(userDTO);
		//중복 0
		int result =0;
		
		if(userDTO== null) {
			result =1; //중복 x
		}
		
		model.addAttribute("result", result);
		
		return "commons/ajax";
	}
	
	@RequestMapping(value = "kakao2", method = RequestMethod.GET)
	public String reviewNestkakaoLogin(@RequestParam String code, Model model, HttpSession session) throws Exception{
		System.out.println("kakaologin2");
	    // 1. 인가 코드 받기

	    // 2. 토큰 받기
	    String accessToken = kakaoApi.getAccessToken2(code);

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
	    	 if (userDTO.getUserOut() == 1) {
		            // 사용자가 비활성화된 상태일 경우 로그인 실패 처리
		            model.addAttribute("result", "비활성화된 사용자입니다. 관리자에게 문의하세요.");
		            model.addAttribute("path", "/reviewNest/login");
		            return "commons/result";  // 비활성화된 사용자 메시지 출력
		        }else {
		        	session.setAttribute("accessToken", accessToken);
		        	session.setAttribute("userEmail", email);
		        	session.setAttribute("userName", nickname);
		        	
		        	session.setAttribute("user", userDTO);
		        	
		        	return "redirect:/reviewNest";		        	
		        }
	    	
	    } else {
	    	model.addAttribute("result", "로그인실패");
	    	model.addAttribute("path", "/reviewNest/login");
	    	return "commons/result";	    	
	    }

	}
}
