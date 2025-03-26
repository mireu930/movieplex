package com.movie.plex.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponService {
	@Autowired
	private CouponDAO couponDAO;
	
	public List<CouponDTO> getList() throws Exception {
		return couponDAO.getList();
	}
}
