package com.movie.plex.theater;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterService {
	
	@Autowired
	private TheaterDAO theaterDAO;
	
	public int addTheater(TheaterDTO theaterDTO) throws Exception{
		return theaterDAO.addTheater(theaterDTO);
	}
	
	public List<TheaterDTO> getList(TheaterDTO theaterDTO) throws Exception{
		return theaterDAO.getList(theaterDTO);
	}
	
	public List<TheaterDTO> getDayList(TheaterDTO theaterDTO) throws Exception{
		return theaterDAO.getDayList(theaterDTO);
	}
	
}
