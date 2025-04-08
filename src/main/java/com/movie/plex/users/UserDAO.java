package com.movie.plex.users;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movie.plex.coupon.CouponDTO;
import com.movie.plex.couponConnect.CouponConnectDTO;
import com.movie.plex.movieBooks.MovieBookDTO;
import com.movie.plex.pages.Pager;
import com.movie.plex.review.ReviewDTO;

@Repository
public class UserDAO {
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE = "com.movie.plex.users.UserDAO.";
	
	public UserDTO getLogin(UserDTO userDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"getLogin", userDTO);
	}
	
	public int join(UserDTO userDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"join", userDTO);
	}
	
	public int kakaoJoin(UserDTO userDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"kakaoJoin", userDTO);
	}
	
	public UserDTO findEmail(String email) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"findEmail", email);
	}
	
	public UserDTO getDetail(String userId) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"getDetail", userId);
	}
	
	public int update(UserDTO userDTO) throws Exception {
		return sqlSession.update(NAMESPACE+"update", userDTO);
	}
	
	public int inactive(UserDTO userDTO) throws Exception{
		return sqlSession.update(NAMESPACE+"inactive", userDTO);
	}
	
	public List<CouponConnectDTO> couponList(UserDTO userDTO) throws Exception {
		return sqlSession.selectList(NAMESPACE+"couponList", userDTO);
	}
	

	public int couponAdd(CouponConnectDTO couponConnectDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"couponAdd", couponConnectDTO);
	}
	
	public int couponUpdate(CouponDTO couponDTO) throws Exception {
		return sqlSession.update(NAMESPACE+"couponUpdate", couponDTO);
	}


	public List<UserDTO> userList(Pager pager) throws Exception {
		return sqlSession.selectList(NAMESPACE+"userList", pager);
	}
	
	public Long getTotalCount(Pager pager) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"getTotalCount", pager);
	}
	
	public int adminUpdate(UserDTO userDTO) throws Exception {
		return sqlSession.update(NAMESPACE+"adminUpdate", userDTO);
	}
	
	public int withdraw(UserDTO userDTO) throws Exception {
		return sqlSession.delete(NAMESPACE+"withdraw", userDTO);
	}
	
	public List<UserDTO> reviewList(Pager pager) throws Exception {
		return sqlSession.selectList(NAMESPACE+"reviewList", pager);
	}
	
	public Long reviewTotalCount(Pager pager) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"reviewTotalCount", pager);
	}
	
	public ReviewDTO reviewDetail(ReviewDTO reviewDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"reviewDetail", reviewDTO);
	}
	
	public List<UserDTO> paymentList(Pager pager) throws Exception {
		return sqlSession.selectList(NAMESPACE+"paymentList", pager);
	}
	
	public Long paymentTotalCount() throws Exception {
		return sqlSession.selectOne(NAMESPACE+"paymentTotalCount");
	}
	
	public int paymentUpdate(UserDTO userDTO) throws Exception {
		return sqlSession.update(NAMESPACE+"paymentUpdate", userDTO);
	}
	
	public List<UserDTO> bookList(Pager pager) throws Exception {
		return sqlSession.selectList(NAMESPACE+"bookList", pager);
	}
	
	public Long bookTotalCount(UserDTO userDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"bookTotalCount", userDTO);
	}
	
	public MovieBookDTO bookDetail(MovieBookDTO movieBookDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"bookDetail", movieBookDTO);
	}
}
