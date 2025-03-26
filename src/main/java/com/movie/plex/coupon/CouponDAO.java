package com.movie.plex.coupon;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CouponDAO {
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE ="com.movie.plex.coupon.CouponDAO.";
	
	public List<CouponDTO> getList() throws Exception {
		return sqlSession.selectList(NAMESPACE+"getList");
	}

}
