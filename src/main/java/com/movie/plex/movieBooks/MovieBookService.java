package com.movie.plex.movieBooks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.plex.movies.MovieDAO;
import com.movie.plex.movies.MovieDTO;
import com.movie.plex.theater.TheaterDAO;
import com.movie.plex.theater.TheaterDTO;

@Service
public class MovieBookService {
	
	@Autowired
	private TheaterDAO theaterDAO;
	@Autowired
	private MovieBookDAO movieBookDAO;

	public TheaterDTO getMovieInfo(Long theaterId) throws Exception{
		return theaterDAO.getMovieInfo(theaterId);
	}
	
	public List<String> getSeats(Long theaterId) throws Exception{
		return movieBookDAO.getSeats(theaterId);
	}

}
