package com.movie.plex.coupon;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponService {
	@Autowired
	private CouponDAO couponDAO;
	
	public List<CouponDTO> getList() throws Exception {
		return couponDAO.getList();
	}
	
	public CouponDTO getCouponByCode(String couponCode) throws Exception {
	    return couponDAO.findCouponByCode(couponCode);
	}

	
	public int couponAdd(CouponDTO couponDTO) throws Exception {
		int result = 0;
		for(int i = 0; i < couponDTO.getCount(); i++) {
	        CouponDTO couponDTO2 = new CouponDTO();
	        couponDTO2.setCouponNum(couponDTO.getCouponNum());
	        couponDTO2.setCouponCode(generateRandomCouponCode());
	        couponDTO2.setCouponName(couponDTO.getCouponName());
	        couponDTO2.setCouponCost(couponDTO.getCouponCost()); 
	        
	        result += couponDAO.couponAdd(couponDTO2);
		}
		return result;
	}
	
	public int couponUpdate(CouponDTO couponDTO) throws Exception {
		return couponDAO.couponUpdate(couponDTO);
	}
	
    public String generateRandomCouponCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 10; i++) { // 10자리 랜덤 코드
            code.append(characters.charAt(random.nextInt(characters.length())));
        }
        return code.toString();
    }
}
