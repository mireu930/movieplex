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
	
	public List<MovieDTO> getMoviesList() throws Exception{
		return movieDAO.getMoviesList();
	}


	public MovieDTO getDetail(MovieDTO movieDTO) {
		return movieDAO.getDetail(movieDTO);
		
	}
}