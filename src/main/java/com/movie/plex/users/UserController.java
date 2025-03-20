package com.movie.plex.users;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/users/*")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private MailSend mailSend;
	
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
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String getLogin() throws Exception {
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
