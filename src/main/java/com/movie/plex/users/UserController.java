package com.movie.plex.users;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
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
		//�ߺ� 0
		int result =0;
		
		if(userDTO== null) {
			result =1; //�ߺ� x
		}
		
		model.addAttribute("result", result);
		
		return "commons/ajax";
	}
	
	@RequestMapping(value = "mailCheck", method = RequestMethod.GET)
	@ResponseBody //�޼ҵ尡 ��ȯ�� ��ü�� �ڵ����� json.xml���·� ��ȯ���ִ� ����, �����͸� ���� Ŭ���̾�Ʈ�� �����Ҥ��� ���
	public String mailCheck(String email) throws Exception {
			UserDTO userDTO = userService.findEmail(email);
			
			if(userDTO != null && email.equals(userDTO.getUserEmail())) {
				System.out.println("�̹�����");
				return mailSend.alreadyEmail();
			} else {	
				System.out.println("���ΰ���");
				return mailSend.joinEmail(email);
			}			
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
		            // ����ڰ� ��Ȱ��ȭ�� ������ ��� �α��� ���� ó��
		            model.addAttribute("result", "��Ȱ��ȭ�� ������Դϴ�. �����ڿ��� �����ϼ���.");
		            model.addAttribute("path", "./login");
		            return "commons/result";  // ��Ȱ��ȭ�� ����� �޽��� ���
		        }else {
		        	session.setAttribute("user", userDTO);
		        	System.out.println(session.getAttribute("user"));
		        	return "redirect:/";
		        }
		}
		
		model.addAttribute("result", "�α��ν���");
		model.addAttribute("path", "./login");
		
		
		return "commons/result";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		String a = "redirect:/";
		
		String accessToken = (String) session.getAttribute("accessToken");

	    // 2. accessToken�� ���� ��� īī�� �α׾ƿ� ����
	    if (accessToken != null) {
	        kakaoApi.kakaoLogout(accessToken);
	        session.removeAttribute("accessToken"); // ��ū ����
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
			model.addAttribute("result", "ȸ�����Լ���");
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
	
	@RequestMapping(value = "couponList", method = RequestMethod.GET)
	@ResponseBody
	public List<UserDTO> couponList(UserDTO userDTO, HttpSession session) throws Exception {
		userDTO = (UserDTO)session.getAttribute("user");
		return userService.couponList(userDTO);
	}
	
	
	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public UserDTO admin(HttpSession session) throws Exception {
		return (UserDTO)session.getAttribute("user");
	}
	
	
	@RequestMapping(value = "reviewLogin", method = RequestMethod.GET)
	public String reviewLogin(Model model) throws Exception {
		model.addAttribute("kakaoApi", kakaoApi.getKakaoApi());
		model.addAttribute("redirectUrl", kakaoApi.getKakaoRedirectUrl());
		return "reviewNest/reviewLogin";
	}
	
	
	
	@RequestMapping(value = "reviewlogin", method = RequestMethod.POST)
	public String reviewLogin(UserDTO userDTO, HttpSession session, Model model) throws Exception {
		userDTO = userService.getLogin(userDTO);
		
		if(userDTO != null) {
			 if (userDTO.getUserOut() == 1) {
		            // ����ڰ� ��Ȱ��ȭ�� ������ ��� �α��� ���� ó��
		            model.addAttribute("result", "��Ȱ��ȭ�� ������Դϴ�. �����ڿ��� �����ϼ���.");
		            model.addAttribute("path", "./reviewNest/login");
		            return "commons/result";  // ��Ȱ��ȭ�� ����� �޽��� ���
		        }else {
		        	session.setAttribute("user", userDTO);
		        	System.out.println(session.getAttribute("user"));
		        	return "redirect:/";
		        }
		}
		
		model.addAttribute("result", "�α��ν���");
		model.addAttribute("path", "./reviewNest/login");
		
		
		return "commons/result";
	}
	
}
