package com.movie.plex.movies;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.movie.plex.nestcontents.NestContentDTO;

@Service
public class MovieService {
	
	@Autowired
	private MovieDAO movieDAO;
	
	public List<MovieDTO> getMainList() throws Exception{
		return movieDAO.getMainList();
	}
	
}
