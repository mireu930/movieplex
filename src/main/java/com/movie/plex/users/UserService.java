package com.movie.plex.users;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.plex.couponConnect.CouponConnectDTO;
import com.movie.plex.pages.Pager;
import com.movie.plex.review.ReviewDTO;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	public UserDTO getLogin(UserDTO userDTO) throws Exception {
		UserDTO result = userDAO.getLogin(userDTO);
		
		if(result != null) {
			if(result.getUserPw().equals(userDTO.getUserPw())) {
				return result;
			}
		}
		
		return null;
	}
	
	public int join(UserDTO userDTO) throws Exception {
		return userDAO.join(userDTO);
	}
	
	public UserDTO idCheck(UserDTO userDTO) throws Exception {
		return userDAO.getLogin(userDTO);
	}
	
	public int kakaoJoin(UserDTO userDTO) throws Exception {
		return userDAO.kakaoJoin(userDTO);
	}
	
	public UserDTO findEmail(String email) throws Exception {
		return userDAO.findEmail(email);
	}
	
	public UserDTO getDetail(String userId) throws Exception {
		return userDAO.getDetail(userId);
	}
	
	public int update(UserDTO userDTO) throws Exception {
		return userDAO.update(userDTO);
	}
	
	public int inactive(UserDTO userDTO) throws Exception {
		return userDAO.inactive(userDTO);
	}
	
	public List<UserDTO> couponList(UserDTO userDTO) throws Exception {
		return userDAO.couponList(userDTO);
	}
	
	public int couponAdd(CouponConnectDTO couponConnectDTO) throws Exception {
		return userDAO.couponAdd(couponConnectDTO);
	}
	
	public List<UserDTO> userList(Pager pager) throws Exception {
		Long totalCount = userDAO.getTotalCount(pager);
		
		pager.makeNum();
		pager.makePage(totalCount);
		
		return userDAO.userList(pager);
	}
	
	public int adminUpdate(UserDTO userDTO) throws Exception {
		return userDAO.adminUpdate(userDTO);
	}
	
	public int withdraw(UserDTO userDTO) throws Exception {
		return userDAO.withdraw(userDTO);
	}
	
	public List<UserDTO> reviewList(Pager pager, HttpSession session, UserDTO userDTO) throws Exception {
		userDTO = (UserDTO)session.getAttribute("user");
		pager.setUserDTO(userDTO);
		Long reviewTotalCount = userDAO.reviewTotalCount(pager);
		
		pager.makeNum();
		pager.makePage(reviewTotalCount);
		
		return userDAO.reviewList(pager);
	}
	
	public ReviewDTO reviewDetail(ReviewDTO reviewDTO) throws Exception {
		return userDAO.reviewDetail(reviewDTO);
	}
	
	public List<UserDTO> paymentList(Pager pager) throws Exception {
		Long paymentTotalCount = userDAO.paymentTotalCount();
		
		pager.makeNum();
		pager.makePage(paymentTotalCount);
		
		return userDAO.paymentList(pager);
	}
}
