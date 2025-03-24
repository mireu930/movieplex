package com.movie.plex.users;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/users/*")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private MailSend mailSend;
	@Autowired
	private KakaoApi kakaoApi;
	
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
	
	@RequestMapping(value = "mailCheck", method = RequestMethod.GET)
	@ResponseBody //메소드가 반환한 객체를 자동으로 json.xml향태로 변환해주는 역할, 데이터를 직접 클라이언트에 전달할ㅇ때 사용
	public String mailCheck(String email) throws Exception {
		return mailSend.joinEmail(email);
	}
	
//	@RequestMapping(value = "/login/oauth2/code/kakao", method = RequestMethod.GET)
//	public String kakaoLogin(@RequestParam String code){
//		System.out.println("kakaologin");
//	    // 1. 인가 코드 받기 (@RequestParam String code)
//
//	    // 2. 토큰 받기
//	    String accessToken = kakaoApi.getAccessToken(code);
//	    System.out.println(accessToken);
//
//	    // 3. 사용자 정보 받기
//	    Map<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);
//
//	    String email = (String)userInfo.get("email");
//	    String nickname = (String)userInfo.get("nickname");
//
//	    System.out.println("email = " + email);
//	    System.out.println("nickname = " + nickname);
//	    System.out.println("accessToken = " + accessToken);
//
//	    return "redirect:/result";
//	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String getLogin(Model model) throws Exception {
		model.addAttribute("kakaoApi", kakaoApi.getKakaoApi());
		model.addAttribute("redirectUrl", kakaoApi.getKakaoRedirectUrl());
		return "users/login";
	}
	
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String getLogin(UserDTO userDTO, HttpSession session, Model model) throws Exception {
		userDTO = userService.getLogin(userDTO);
		
		if(userDTO != null) {
			session.setAttribute("user", userDTO);
			return "redirect:/";
		}
		
		model.addAttribute("result", "로그인실패");
		model.addAttribute("path", "./login");
		
		
		return "commons/result";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		String accessToken = (String) session.getAttribute("accessToken");

	    // 2. accessToken이 있을 경우 카카오 로그아웃 수행
	    if (accessToken != null) {
	        kakaoApi.kakaoLogout(accessToken);
	        session.removeAttribute("accessToken"); // 토큰 삭제
	    }
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value = "join",method = RequestMethod.GET)
	public String join() throws Exception {
		return "users/join";
	}
	
	@RequestMapping(value = "join", method = RequestMethod.POST)
	public String join(UserDTO userDTO, Model model) throws Exception {
		
		int result = userService.join(userDTO);
		
		if(result > 0) {
			model.addAttribute("result", "회원가입성공");
			model.addAttribute("path", "../");
		}
		
		return "commons/result";
	}
	
}
