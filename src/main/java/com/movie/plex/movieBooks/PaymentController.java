package com.movie.plex.movieBooks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.movie.plex.users.UserDTO;

@Controller
@RequestMapping("/moviePayment/*")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	
//	//결제 금액이 맞는지 확인하는 기능
//	@RequestMapping(value="payment/complete", method=RequestMethod.POST)
//	public String paymentComplete(String imp_uid, String merchant_uid, Long totalPrice, Long bookId, Model model) throws Exception{
//		System.out.println(imp_uid);
//		System.out.println(merchant_uid);
//		System.out.println(totalPrice);
//		System.out.println("complete");
//		int result = paymentService.checkAmounts(imp_uid, merchant_uid, totalPrice, bookId);
//		
//		model.addAttribute("result", result);
//		
//		return "/commons/ajax";
//		
//	}
	
	//동일한 이름으로 여러개 받을 때는 RequestParam 사용!
	@RequestMapping(value="movieBookCard", method=RequestMethod.POST)
	public String movieBookCard(@RequestParam("seat") List<String> seat, Long theaterId, Long totalPrice, String usedCoupon, String imp_uid, String merchant_uid, HttpSession session, Model model) throws Exception{
		System.out.println("payment");
		System.out.println(seat.size());
		System.out.println(theaterId);
		UserDTO userDTO = (UserDTO)session.getAttribute("user");
		Long result = paymentService.movieBookCard(seat, theaterId, userDTO, totalPrice, usedCoupon, imp_uid, merchant_uid);
		model.addAttribute("result", result);
		
		return "commons/ajax";
	}
	@ResponseBody
	@RequestMapping(value="getUserInfo", method=RequestMethod.POST)
	public Map<String, Object> getUserInfo(HttpSession session) throws Exception{
		UserDTO userDTO = (UserDTO)session.getAttribute("user");
		Map<String, Object> info = paymentService.getUserInfo(userDTO);
		
		return info;
	}
	@RequestMapping(value="movieBookBankBook", method=RequestMethod.POST)
	public String movieBookBankBook(@RequestParam("seat") List<String> seat, Long theaterId, Long totalPrice, String usedCoupon,  HttpSession session, Model model) throws Exception{
		UserDTO userDTO = (UserDTO)session.getAttribute("user");
		Map<String, Object> result = paymentService.movieBookBankBook(seat, theaterId, totalPrice, userDTO, usedCoupon);
		
		model.addAttribute("theaterDTO", result.get("theaterDTO"));
		model.addAttribute("seats", result.get("seats"));
		model.addAttribute("totalPrice", result.get("totalPrice"));
		
		return "/movieBooks/bookSuccessBankPage";
	}
}
