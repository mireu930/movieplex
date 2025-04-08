package com.movie.plex;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.movie.plex.movies.MovieDTO;
import com.movie.plex.movies.MovieJson;

public class MovieTest extends SampleTestCase{
	
	@Autowired
	private MovieJson jsonService;
	
	@Value("${tmdb.apiKey}")
	private String tmdbApiKey;
	
	@Test
	public void addJsonListTest() throws Exception{
//		int result = jsonService.addListJson();
		List<MovieDTO> result = jsonService.addListJson();
		
//		for(MovieDTO dto : dtos) {
//			System.out.println(dto.getMovieId());
//		}
		assertNotEquals(0, result.size());
	}
	
	//@Test
	public void init() {
	    System.out.println("API KEY: " + tmdbApiKey); // 여기에 null 이면 설정 안 된 것
	}

}
