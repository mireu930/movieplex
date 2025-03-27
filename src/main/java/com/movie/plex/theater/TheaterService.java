package com.movie.plex.theater;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterService {
	
	@Autowired
	private TheaterDAO theaterDAO;
	
	public int addTheater(TheaterDTO theaterDTO) throws Exception{
		int result = theaterDAO.checkTime(theaterDTO);
		if(result == 0) {
			theaterDAO.addTheater(theaterDTO);
			return 1;
		} 
		return 0;
	}
	
	public List<TheaterDTO> getList(TheaterDTO theaterDTO) throws Exception{
		return theaterDAO.getList(theaterDTO);
	}
	
	public List<TheaterDTO> getDayList(TheaterDTO theaterDTO) throws Exception{
		return theaterDAO.getDayList(theaterDTO);
	}
	public int checkTime(TheaterDTO theaterDTO) throws Exception{
		return theaterDAO.checkTime(theaterDTO);
	}

	public int deleteTheater(TheaterDTO theaterDTO) {
		int result = theaterDAO.deleteCheck(theaterDTO);
		System.out.println("!!!!!!" + result);
		if(result == 0) {
			result = theaterDAO.deleteTheater(theaterDTO);
			return result;
		}
		return 0;
	}
}
