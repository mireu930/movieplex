package com.movie.plex.users;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.movie.plex.coupon.CouponDTO;
import com.movie.plex.coupon.CouponService;
import com.movie.plex.couponConnect.CouponConnectDTO;
import com.movie.plex.movieBooks.MovieBookDTO;
import com.movie.plex.pages.Pager;
import com.movie.plex.review.ReviewDTO;

@Controller
@RequestMapping(value = "/users/*")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private MailSend mailSend;
	@Autowired
	private KakaoApi kakaoApi;
	
	@RequestMapping(value = "check", method = RequestMethod.GET)
	public String idCheck(UserDTO userDTO, Model model) throws Exception {
		
		userDTO = userService.idCheck(userDTO);
		//占쌩븝옙 0
		int result =0;
		
		if(userDTO== null) {
			result =1; //占쌩븝옙 x
		}
		
		model.addAttribute("result", result);
		
		return "commons/ajax";
	}
	
	@RequestMapping(value = "mailCheck", method = RequestMethod.GET)
	@ResponseBody //占쌨소드가 占쏙옙환占쏙옙 占쏙옙체占쏙옙 占쌘듸옙占쏙옙占쏙옙 json.xml占쏙옙占승뤄옙 占쏙옙환占쏙옙占쌍댐옙 占쏙옙占쏙옙, 占쏙옙占쏙옙占싶몌옙 占쏙옙占쏙옙 클占쏙옙占싱억옙트占쏙옙 占쏙옙占쏙옙占쌀ㅿ옙占쏙옙 占쏙옙占�
	public String mailCheck(String email) throws Exception {
			UserDTO userDTO = userService.findEmail(email);
			
			if(userDTO != null && email.equals(userDTO.getUserEmail())) {
				System.out.println("占싱뱄옙占쏙옙占쏙옙");
				return mailSend.alreadyEmail();
			} else {	
				System.out.println("占쏙옙占싸곤옙占쏙옙");
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
		            // 占쏙옙占쏙옙微占� 占쏙옙활占쏙옙화占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占� 占싸깍옙占쏙옙 占쏙옙占쏙옙 처占쏙옙
		            model.addAttribute("result", "占쏙옙활占쏙옙화占쏙옙 占쏙옙占쏙옙占쏙옙都求占�. 占쏙옙占쏙옙占쌘울옙占쏙옙 占쏙옙占쏙옙占싹쇽옙占쏙옙.");
		            model.addAttribute("path", "./login");
		            return "commons/result";  // 占쏙옙활占쏙옙화占쏙옙 占쏙옙占쏙옙占� 占쌨쏙옙占쏙옙 占쏙옙占�
		        }else {
		        	session.setAttribute("user", userDTO);
		        	
		        	return "redirect:/";
		        }
		}
		
		model.addAttribute("result", "占싸깍옙占싸쏙옙占쏙옙");
		model.addAttribute("path", "./login");
		
		
		return "commons/result";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		String a = "redirect:/";
		
		String accessToken = (String) session.getAttribute("accessToken");

	    // 2. accessToken占쏙옙 占쏙옙占쏙옙 占쏙옙占� 카카占쏙옙 占싸그아울옙 占쏙옙占쏙옙
	    if (accessToken != null) {
	        kakaoApi.kakaoLogout(accessToken);
	        session.removeAttribute("accessToken"); // 占쏙옙큰 占쏙옙占쏙옙
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
			model.addAttribute("result", "회占쏙옙占쏙옙占쌉쇽옙占쏙옙");
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
	public List<CouponConnectDTO> couponList(UserDTO userDTO, HttpSession session) throws Exception {
		 userDTO = (UserDTO)session.getAttribute("user");
		 
		 List<CouponConnectDTO> couponConnectDTOs = userService.couponList(userDTO);
		 
		 List<CouponConnectDTO> filteCoupons = new ArrayList<CouponConnectDTO>();
		 for(CouponConnectDTO coupon: couponConnectDTOs) {
			 if(coupon.getUsed()==0) {
				 filteCoupons.add(coupon);
			 }
		 }
		 
		return filteCoupons;
	}
	
	@RequestMapping(value = "getCouponByCode", method = RequestMethod.GET)
	@ResponseBody
	public CouponDTO getCouponByCode(@RequestParam("couponCode") String couponCode) throws Exception {
	    return couponService.getCouponByCode(couponCode);
	}
	
	@RequestMapping(value = "couponAdd", method = RequestMethod.POST)
	@ResponseBody
	public int couponAdd(CouponConnectDTO couponConnectDTO, HttpSession session, @RequestParam("couponNum") Long couponNum) throws Exception {
		 UserDTO userDTO = (UserDTO) session.getAttribute("user");
		 
		 System.out.println(userDTO.getUserNum());
		 System.out.println(couponNum);
		couponConnectDTO.setUserNum(userDTO.getUserNum());
		couponConnectDTO.setCouponNum(couponNum);
		return userService.couponAdd(couponConnectDTO);
	}
	
	@RequestMapping(value = "reviewList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> reviewList(Pager pager, UserDTO userDTO, HttpSession session) throws Exception {
		userDTO = (UserDTO)session.getAttribute("user");
		pager.setUserDTO(userDTO);
		List<UserDTO> list = userService.reviewList(pager,session,userDTO);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", list);
		map.put("pager", pager);
		return map;
	}
	
	@RequestMapping(value = "reviewDetail", method = RequestMethod.GET)
	@ResponseBody
	public ReviewDTO reviewDetail(ReviewDTO reviewDTO) throws Exception {
		reviewDTO = userService.reviewDetail(reviewDTO);
		return reviewDTO;
	}
	
	@RequestMapping(value = "paymentList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> paymentList(Pager pager, UserDTO userDTO, HttpSession session) throws Exception {
		userDTO = (UserDTO)session.getAttribute("user");
		pager.setUserDTO(userDTO);
		List<UserDTO> list = userService.paymentList(pager);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", list);
		map.put("pager", pager);
		
		return map;
	}
	
	@RequestMapping(value = "bookList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> bookList(Pager pager,UserDTO userDTO, HttpSession session) throws Exception {
		userDTO = (UserDTO)session.getAttribute("user");
		pager.setUserDTO(userDTO);
		List<UserDTO> list = userService.bookList(pager, userDTO, session);

		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", list);
		map.put("pager", pager);
		return map;
	}
	
	@RequestMapping(value = "bookDetail", method = RequestMethod.GET)
	@ResponseBody
	public MovieBookDTO bookDetail(MovieBookDTO movieBookDTO) throws Exception {
		return userService.bookDetail(movieBookDTO);
	}
	
	@RequestMapping(value = "userCouponUpdate", method = RequestMethod.POST)
	@ResponseBody
	public int couponUpdate(@RequestParam("couponNum") Long couponNum) throws Exception {
		  CouponDTO couponDTO = new CouponDTO();
		    couponDTO.setCouponNum(couponNum); 
		
		int result = userService.couponUpdate(couponDTO);
		int result2 = couponService.couponUpdate(couponDTO);

		return (result>0&&result2>0)?1:0;
	}
}
