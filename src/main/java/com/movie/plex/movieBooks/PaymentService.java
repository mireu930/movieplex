package com.movie.plex.movieBooks;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.movie.plex.coupon.CouponDAO;
import com.movie.plex.coupon.CouponDTO;
import com.movie.plex.couponConnect.CouponConnectDTO;
import com.movie.plex.movies.MovieDAO;
import com.movie.plex.users.UserDAO;
import com.movie.plex.users.UserDTO;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;


@Service
public class PaymentService {

	@Autowired
	private MovieBookService bookService;
	
	@Autowired
	private CouponDAO couponDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private MovieBookDAO bookDAO;
	
	@Value("${import.channel}")
	private String importChannel;
	
	@Value("${import.rest}")
	private String rest_api;
	@Value("${import.secret}")
	private String api_secret;
	
	//private IamportClient api = new IamportClient(rest_api, api_secret);
	
	
	public Long movieBookCard(List<String> seat, Long theaterId, UserDTO userDTO, Long totalPrice, String usedCoupon, String imp_uid, String merchant) throws Exception{
		int check = checkAmounts(imp_uid, totalPrice);
		Long bookId = 0L;
		if(check == 1) {
			bookId = bookService.movieBookCard(seat, theaterId, userDTO, totalPrice, merchant);
			if(usedCoupon != "") {
				CouponDTO couponDTO = new CouponDTO();
				couponDTO.setCouponNum(Long.parseLong(usedCoupon));
				userDAO.couponUpdate(couponDTO);
				couponDAO.couponUpdate(couponDTO);
			}
		}
		return bookId;
	}
	
	public Map<String, Object> getUserInfo(UserDTO userDTO) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("importChannel", importChannel);
		info.put("userEmail", userDTO.getUserEmail());
		info.put("userName", userDTO.getUserName());
		info.put("userPhone", userDTO.getUserPhone());
		
		return info;
	}

	

	public Map<String, Object> movieBookBankBook(List<String> seat, Long theaterId, Long totalPrice, UserDTO userDTO,String usedCoupon) throws Exception{
		Long bookId = bookService.movieBookBankBook(seat, theaterId, totalPrice, userDTO);
		if(usedCoupon != "") {
			CouponDTO couponDTO = new CouponDTO();
			couponDTO.setCouponNum(Long.parseLong(usedCoupon));
			userDAO.couponUpdate(couponDTO);
			couponDAO.couponUpdate(couponDTO);
		}
		
		Map<String, Object> map = bookService.bookSuccessPage(bookId);
		return map;
	}
	private int checkAmounts(String imp_uid, Long totalPrice) throws Exception{
		IamportClient api = new IamportClient(rest_api, api_secret);
		System.out.println("금액 확인 메서드");
//		System.out.println(rest_api);
//		System.out.println(api_secret);
		IamportResponse<Payment> payment = api.paymentByImpUid(imp_uid);
		BigDecimal amounts = BigDecimal.valueOf(totalPrice);
		int result = 0;
		if(amounts.equals(payment.getResponse().getAmount())) {
			return 1;
		} else {
			calcelPayment(imp_uid, "금액이 불일치합니다", api);
			//환불처리 로직은 나중에~~
			//bookService.deleteInfo(bookId);
			result = 0;
		}
		
		return result;
	}
	
	public void deleteBook(Long bookId) throws Exception{
		IamportClient api = new IamportClient(rest_api, api_secret);
		Long merchant = bookDAO.getPayId(bookId);
		calcelPayment(merchant.toString(), "환불", api);
	}
	
	private void calcelPayment(String id, String reason, IamportClient api) throws Exception{
		CancelData cancelData = new CancelData(id, false);
		cancelData.setReason(reason);
		
		IamportResponse<Payment> response = api.cancelPaymentByImpUid(cancelData);
		
		if(response.getResponse() != null) {
			System.out.println("결제 취소 성공");
		}else {
			System.out.println("결제 취소 실패");
		}
	}

	



	
}
