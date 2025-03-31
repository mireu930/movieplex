package com.movie.plex.pages;

public class Pager {

	private Long startNum;
	private Long lastNum;
	private Long page;
	private Long perPage;
	private Long start;
	private Long end;
	private boolean endCheck;
	
	private String kind;
	private String search;
	
	public boolean isEndCheck() {
		return endCheck;
	}
	
	public void setEndCheck(boolean endCheck) {
		this.endCheck = endCheck;
	}
	
	public void makeNum() throws Exception {
		this.startNum = (this.getPage()-1)*this.getPerPage()+1;
		this.lastNum = this.getPage()*this.getPerPage();
	}
	
	public void makePage(Long totalCount) throws Exception {
		if(totalCount<1) {
			totalCount=1L;
		}
		
		Long totalPage = totalCount/5;
		
		if(totalCount % 5 != 0) {
			totalPage++;
		}
		
		Long totalBlock = (long)Math.ceil(totalPage/5.0);
		Long curBlock = (long)Math.ceil(this.getPage()/5.0);
		
		Long start = (curBlock-1)*5+1;
		Long end = curBlock*5;
		
		this.setStart(start);
		this.setEnd(end);
		
		if(totalBlock==curBlock) {
			this.setEnd(totalPage);
			this.setEndCheck(true);
		}
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
	
	public Long getStartNum() {
		return startNum;
	}
	
	public void setStartNum(Long startNum) {
		this.startNum = startNum;
	}
	
	public Long getLastNum() {
		return lastNum;
	}
	
	public void setLastNum(Long lastNum) {
		this.lastNum = lastNum;
	}
	
	public Long getPage() {
		if(page == null || page <1) {
			this.page =1L;
		}
		return page;
	}
	
	public void setPage(Long page) {
		this.page = page;
	}
	
	public Long getPerPage() {
		if(perPage==null|| perPage<1) {
			this.perPage =5L;
		}
		return perPage;
	}
	
	public void setPerPage(Long perPage) {
		this.perPage = perPage;
	}
	
	public String getKind() {
		if(this.kind == null) {
			this.kind = "k1";
		}
		return kind;
	}
	
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	public String getSearch() {
		if(this.search == null) {
			this.search="";
		}
		return search;
	}
	
	public void setSearch(String search) {
		this.search = search;
	}
	
	
}
