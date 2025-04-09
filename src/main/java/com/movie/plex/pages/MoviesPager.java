package com.movie.plex.pages;

import org.apache.taglibs.standard.tag.el.core.ForTokensTag;

public class MoviesPager {
	private Long startNum;
	private Long endNum;
	
	private Long page;
	
	private Long perPage;
	
	private Long start;
	private Long end;
	
	private boolean endCheck;
	
	private String search;
	
	
	public Long getStartNum() {
		return startNum;
	}
	public void setStartNum(Long startNum) {
		this.startNum = startNum;
	}
	public Long getEndNum() {
		return endNum;
	}
	public void setEndNum(Long endNum) {
		this.endNum = endNum;
	}
	public Long getPage() {
		
		if(this.page == null || page <= 0) {
			this.page = 1L;
		}
		return page;
	}
	public void setPage(Long page) {
		this.page = page;
	}
	public Long getPerPage() {
		
		if(this.perPage == null) {
			this.perPage = 12L;
		}
		return perPage;
	}
	public void setPerPage(Long perPage) {
		this.perPage = perPage;
	}
	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	}
	public Long getEnd() {
		return end;
	}
	public void setEnd(Long end) {
		this.end = end;
	}
	public String getSearch() {
		if(this.search == null) {
			this.search = "";
		}
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
	public boolean isEndCheck() {
		return endCheck;
	}
	public void setEndCheck(boolean endCheck) {
		this.endCheck = endCheck;
	}
	public void makeNum() {
		startNum = (getPage() - 1) * getPerPage() + 1;
		endNum = getPage() * getPerPage();
	}
	
	public void make(Long totalCount) {
		//1. 
		Long totalPage = totalCount / 12;
		if(totalCount % 12 != 0) {
			totalPage++;
		}
		//2.
		Long totalBlock = totalPage / 5;
		if(totalPage % 5 != 0) {
			totalBlock++;
		}
		//3. page번호로 Block 번호 구하기
		Long curBlock = this.getPage() / 5;
		if(this.getPage() % 5 != 0) {
			curBlock++;
		}
		
		Long start = (curBlock - 1) * 5 + 1;
		Long end = curBlock * 5;
		
		this.setEnd(end);
		this.setStart(start);
		
		if(totalBlock == curBlock) {
			this.setEnd(totalPage);
			this.setEndCheck(true);
		}
	}
}
