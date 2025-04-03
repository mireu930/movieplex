package com.movie.plex.movieBooks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	
	
	@RequestMapping(value="payment/complete", method=RequestMethod.POST)
	public void paymentComplete(String imp_uid, String merchant_uid, Long totalPrice) throws Exception{
		System.out.println(imp_uid);
		System.out.println(merchant_uid);
		System.out.println(totalPrice);
		System.out.println("complete");
	}
	
	//동일한 이름으로 여러개 받을 때는 RequestParam 사용!
	@ResponseBody
	@RequestMapping(value="moviePayment/movieBookInfo", method=RequestMethod.POST)
	public Map<String, Object> movieBookInfo(@RequestParam("seat") List<String> seat, Long theaterId, Long totalPrice, HttpSession session) throws Exception{
		System.out.println("payment");
		System.out.println(seat.size());
		System.out.println(theaterId);
		UserDTO userDTO = (UserDTO)session.getAttribute("user");
		Map<String, Object> result = paymentService.movieBookInfo(seat, theaterId, userDTO, totalPrice);
		
		return result;
	}
}
