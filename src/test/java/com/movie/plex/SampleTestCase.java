package com.movie.plex;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) //Junit 을 사용할게!
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml",}) 
public abstract class SampleTestCase {

	
}
