package com.movie.plex.movies;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.movie.plex.nestcontents.NestContentDTO;
import com.movie.plex.pages.MoviesPager;

@Service
public class MovieService {
	
	@Autowired
	private MovieDAO movieDAO;
	
	
	public List<MovieDTO> getMainList() throws Exception{
		System.out.println(movieDAO.getMainList().size());
		return movieDAO.getMainList();
	}
	

	public List<MovieDTO> getList() throws Exception{
		return movieDAO.getList();		
	}
	
	public MovieDTO getMovieTitle(MovieDTO movieDTO) throws Exception{
		return movieDAO.getMovieTitle(movieDTO);
	}
	
	public List<MovieDTO> getMoviesList(MoviesPager pager) throws Exception{
		Long total = movieDAO.getTotalMovies(pager);
		//System.out.println(total);
		pager.make(total);
		
		pager.makeNum();
		List<MovieDTO> dtos = movieDAO.getMoviesList(pager);
		System.out.println("startNum" + pager.getStartNum());
		System.out.println("endNum" + pager.getEndNum());
		System.out.println("start" + pager.getStart());
		System.out.println("end" + pager.getEnd());
		return dtos;
	}


	public MovieDTO getDetail(MovieDTO movieDTO) throws Exception{
		return movieDAO.getDetail(movieDTO);
	}
}