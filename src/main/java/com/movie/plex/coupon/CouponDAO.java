package com.movie.plex.coupon;

import java.util.List;
import java.util.UUID;

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
	
	public int couponAdd(CouponDTO couponDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"couponAdd", couponDTO);
	}
	
	public CouponDTO findCouponByCode(String couponCode) throws Exception {
	    return sqlSession.selectOne(NAMESPACE+"findCouponByCode", couponCode);
	}
	
	public int couponUpdate(CouponDTO couponDTO) throws Exception {
		return sqlSession.update(NAMESPACE+"couponUpdate", couponDTO);
	}
}
