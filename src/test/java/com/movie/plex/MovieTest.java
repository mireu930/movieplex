package com.movie.plex;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.movie.plex.movies.MovieDTO;
import com.movie.plex.movies.MovieJsonService;

public class MovieTest extends SampleTestCase{
	
	@Autowired
	private MovieJsonService jsonService;
	
	@Test
	public void addJsonListTest() throws Exception{
		int result = jsonService.addJsonList();
		
//		for(MovieDTO dto : dtos) {
//			System.out.println(dto.getMovieId());
//		}
		assertNotEquals(0, result);
		//pr test
	}

}
