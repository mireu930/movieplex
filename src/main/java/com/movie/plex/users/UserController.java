package com.movie.plex.users;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

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
	
	@RequestMapping(value = "forgetPw", method = RequestMethod.GET)
	public String forgetPw(String email, String pw, Model model) throws Exception {
		
		UserDTO userDTO = userService.findEmail(email);
		
		if(userDTO!=null&&email.equals(userDTO.getUserEmail())) {
			userDTO = userService.getDetail(userDTO.getUserId());
			pw = userDTO.getUserPw();
			
		} 
		return mailSend.forgetEmail(email, pw);
	}
	
	
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
			 if (userDTO.getUserOut() == 1) {
		            // 사용자가 비활성화된 상태일 경우 로그인 실패 처리
		            model.addAttribute("result", "비활성화된 사용자입니다. 관리자에게 문의하세요.");
		            model.addAttribute("path", "./login");
		            return "commons/result";  // 비활성화된 사용자 메시지 출력
		        }else {
		        	session.setAttribute("user", userDTO);
		        	return "redirect:/";
		        }
		}
		
		model.addAttribute("result", "로그인실패");
		model.addAttribute("path", "./login");
		
		
		return "commons/result";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		String a = "redirect:/";
		
		String accessToken = (String) session.getAttribute("accessToken");

	    // 2. accessToken이 있을 경우 카카오 로그아웃 수행
	    if (accessToken != null) {
	        kakaoApi.kakaoLogout(accessToken);
	        session.removeAttribute("accessToken"); // 토큰 삭제
	        String kakaoUrl = "https://kauth.kakao.com/oauth/logout?client_id="+kakaoApi.getKakaoApi()+"&logout_redirect_uri=http://localhost/users/login";
	        a = "redirect:"+kakaoUrl;
	    }
	    session.invalidate();
		return a;
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
	
	@RequestMapping(value = "mypage", method = RequestMethod.GET)
	public UserDTO mypage(UserDTO userDTO, HttpSession session, Model model) throws Exception {
		return (UserDTO)session.getAttribute("user");
	}
	
	@RequestMapping(value = "mypageData", method = RequestMethod.GET)
	@ResponseBody
	public UserDTO getUserInfo(HttpSession session) {
	    UserDTO userDTO = (UserDTO) session.getAttribute("user");
	    return userDTO;
	}
	
	@RequestMapping(value = "update",method = RequestMethod.POST)
	@ResponseBody
	public UserDTO  update(HttpSession session,
					@RequestParam("userId") String userId, 
					@RequestParam("userName") String userName,
					@RequestParam("userEmail") String userEmail,
					@RequestParam("userPhone") String userPhone,
					@RequestParam("userPw") String userPw) throws Exception {
		System.out.println("update");
		System.out.println(userId);
		System.out.println(userName);
		System.out.println(userEmail);
		System.out.println(userPhone);
		System.out.println(userPw);
		
		UserDTO userDTO = userService.getDetail(userId);
		
		userDTO.setUserName(userName);
		userDTO.setUserEmail(userEmail);
		userDTO.setUserPhone(userPhone);
		userDTO.setUserPw(userPw);
		
		int result = userService.update(userDTO);
		
		if(result>0) {
			userDTO = userService.getDetail(userId);
			session.setAttribute("user", userDTO);
			return userDTO;
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public int delete(@RequestParam("userId") String userid, HttpSession session) throws Exception {
		System.out.println("delete");
		UserDTO userDTO = userService.getDetail(userid);
		userDTO.setUserOut(1L);
		
		int result = userService.inactive(userDTO);
		session.invalidate();
		return result;
	}
	
	
	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public UserDTO admin(HttpSession session) throws Exception {
		return (UserDTO)session.getAttribute("user");
	}
	
}
