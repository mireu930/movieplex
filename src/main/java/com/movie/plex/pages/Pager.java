package com.movie.plex.pages;
import com.movie.plex.users.UserDTO;

public class Pager {
	private Long startNum;
	private Long lastNum;
	private Long page;
	private Long perPage;
	private Long start;
	private Long end;
	private boolean endCheck;
	
	private UserDTO userDTO;
	

	private String kind;
	private String search;
	
	public UserDTO getUserDTO() {
		return userDTO;
	}
	
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	
	public boolean isEndCheck() {
		return endCheck;
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
	
	
	public Long getLastNum() {
		return lastNum;
	}
	
	public void setLastNum(Long lastNum) {
		this.lastNum = lastNum;
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
