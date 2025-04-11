package com.movie.plex;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.movie.plex.nestcontents.NestContentJson;

public class ReviewNestTest extends SampleTestCase{
	
	@Autowired
	private NestContentJson nestContentJson;
	
	
	

//	  @Test public void addJsonList() throws Exception {
//	 
//	 int result = nestContentJson.addJsonList();
//	 
//	 assertNotEquals(0, result); 
//	 }
	 
	 

	 
	
	
	
	
		
		  @Test public void addJsonTVList() throws Exception {
		  
		  int result = nestContentJson.addJsonTVList();
		  
		  assertNotEquals(0, result); }
		 


	 
	 
}

