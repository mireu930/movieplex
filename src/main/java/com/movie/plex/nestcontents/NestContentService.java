package com.movie.plex.nestcontents;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.plex.pages.Pager;

@Service
public class NestContentService {
	
	@Autowired
	private NestContentDAO nestContentDAO;
	
	public List<NestContentDTO> getMovieList(Pager pager) throws Exception{
		//珥앷갗�닔 媛��졇���꽌 怨꾩궛�븯�뒗 �떇
		Long totalCount = nestContentDAO.getTotalCount(pager);
		
		pager.makePage(totalCount);
		
		pager.makeNum();
		List<NestContentDTO> movieList = nestContentDAO.getMovieList(pager);
		//System.out.println("Service getMovieList");
		
		return movieList;
	}
	
	public List<NestContentDTO> getTvList(Pager pager) throws Exception{
		Long tvtotalCount = nestContentDAO.getTvTotalCount(pager);
	
		pager.makePage(tvtotalCount);
		
		pager.makeNum();
		List<NestContentDTO> tvList = nestContentDAO.getTvList(pager);
		System.out.println(tvtotalCount);
		return tvList;
	}
	
	public NestContentDTO getMovieDetail(NestContentDTO nestContentDTO) throws Exception {
		return nestContentDAO.getMovieDetail(nestContentDTO);
	}
	
	public NestContentDTO getTvDetail(NestContentDTO nestContentDTO) throws Exception {
		return nestContentDAO.getTvDetail(nestContentDTO);
	}
}
