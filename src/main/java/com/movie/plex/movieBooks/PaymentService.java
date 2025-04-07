package com.movie.plex.movieBooks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.movie.plex.coupon.CouponDAO;
import com.movie.plex.couponConnect.CouponConnectDTO;
import com.movie.plex.movies.MovieService;
import com.movie.plex.users.UserDTO;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import oracle.jdbc.driver.parser.Parseable;

@Service
public class PaymentService {

	@Autowired
	private MovieBookService bookService;
	
	@Autowired
	private CouponDAO couponDAO;
	
	@Value("${import.channel}")
	private String importChannel;
	
	@Value("${import.rest}")
	private String rest_api;
	@Value("${import.secret}")
	private String api_secret;
	
	
	public Map<String, Object> movieBookCard(List<String> seat, Long theaterId, UserDTO userDTO, Long totalPrice, String usedCoupon) throws Exception{
		Long bookId = bookService.movieBookCard(seat, theaterId, userDTO, totalPrice);
		if(usedCoupon != "") {
			CouponConnectDTO connectDTO = new CouponConnectDTO();
			connectDTO.setUserNum(userDTO.getUserNum());
			connectDTO.setCouponNum(Long.parseLong(usedCoupon));
			couponDAO.couponUsed(connectDTO);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("bookId", bookId);
		result.put("importChannel", importChannel);
		result.put("userName", userDTO.getUserName());
		result.put("userEmail", userDTO.getUserEmail());
		result.put("userPhone", userDTO.getUserPhone());
		return result;
	}

	public int checkAmounts(String imp_uid, String merchant_uid, Long totalPrice, Long bookId) throws Exception{
		IamportClient api = new IamportClient(rest_api, api_secret);
		System.out.println("금액 확인 메서드");
		System.out.println(rest_api);
		System.out.println(api_secret);
		IamportResponse<Payment> payment = api.paymentByImpUid(imp_uid);
		BigDecimal amounts = bookService.getAmounts(bookId);
		int result = 0;
		if(amounts.equals(payment.getResponse().getAmount())) {
			result = bookService.updateNowStatus(bookId);
		} else {
			calcelPayment(imp_uid, "금액이 불일치합니다", api);
			//환불처리 로직은 나중에~~
			//bookService.deleteInfo(bookId);
			result = 0;
		}
		
		return result;
	}
	
	private void calcelPayment(String impUid, String reason, IamportClient api) throws Exception{
		CancelData cancelData = new CancelData(impUid, false);
		cancelData.setReason(reason);
		
		IamportResponse<Payment> response = api.cancelPaymentByImpUid(cancelData);
		
		if(response.getResponse() != null) {
			System.out.println("결제 취소 성공");
		}else {
			System.out.println("결제 취소 실패");
		}
	}

	public Map<String, Object> movieBookBankBook(List<String> seat, Long theaterId, Long totalPrice, UserDTO userDTO,String usedCoupon) throws Exception{
		Long bookId = bookService.movieBookBankBook(seat, theaterId, totalPrice, userDTO);
		if(usedCoupon != "") {
			CouponConnectDTO connectDTO = new CouponConnectDTO();
			connectDTO.setUserNum(userDTO.getUserNum());
			connectDTO.setCouponNum(Long.parseLong(usedCoupon));
			couponDAO.couponUsed(connectDTO);
		}
		
		Map<String, Object> map = bookService.bookSuccessPage(bookId);
		return map;
	}
}
