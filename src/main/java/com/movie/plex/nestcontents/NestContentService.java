package com.movie.plex.nestcontents;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.plex.like.ContentsLikeDAO;
import com.movie.plex.pages.Pager;
import com.movie.plex.pages.ReviewPager;

@Service
public class NestContentService {
	
	@Autowired
	private NestContentDAO nestContentDAO;
	@Autowired
	private ContentsLikeDAO contentsLikeDAO;
	
	public List<NestContentDTO> getMovieList(ReviewPager pager) throws Exception{
		Long totalCount = nestContentDAO.getTotalCount(pager);
		/*
		 * System.out.println("컨텐츠 갯수: "+totalCount); System.out.println("!!");
		 */
		pager.make(totalCount);
		
		pager.makeNum();
		List<NestContentDTO> movieList = nestContentDAO.getMovieList(pager);
		//System.out.println("Service getMovieList");
		
		return movieList;
	}
	
	public List<NestContentDTO> getTvList(ReviewPager pager) throws Exception{
		Long tvtotalCount = nestContentDAO.getTvTotalCount(pager);
		
		pager.make(tvtotalCount);
		
		pager.makeNum();
		List<NestContentDTO> tvList = nestContentDAO.getTvList(pager);
		return tvList;
	}
	
	public NestContentDTO getMovieDetail(Long contentId) throws Exception {
		return nestContentDAO.getMovieDetail(contentId);
	}
	
	public NestContentDTO getTvDetail(Long contentId) throws Exception {
		return nestContentDAO.getTvDetail(contentId);
	}
	
	
	
	
	
	
	
	
	
	
}
