package com.movie.plex.pages;

public class Pager {
		
		//시작 	번호
		private Long startNum;
		
		//끝번호
		private Long endNum;
		
		//페이지 번호(파라미터 대입)
		private Long page;
		
		//한 페이지당 출력할 컨텐츠 갯수
		private Long perPage;
		
		private Long start;
		private Long end;
		private boolean endCheck;
		
		//시작 번호, 끝번호를 계산하는 메서드 
		public void makeNum() {
				this.startNum = (this.getPage()-1)*this.getPerPage()+1;
				this.endNum = this.getPage()*this.getPerPage();
		}
		
		public void make(Long totalCount) {
				//최소값 1로 설정해서 비정상적인 값 방어하기 위함
				if(totalCount<1) {
					totalCount=1L;
			}
				//1. TotalPage
				Long totalPage = totalCount/20;
				if(totalCount%20 != 0) {
					totalPage++;
				}
				
				//2. TotalBlock
				Long totalBlock = totalPage/5;
				if(totalPage %5 != 0) {
					totalBlock++;
				}
				
				//3. page번호로 Block 번호 구하기
				Long curBlock = this.getPage()/5;
				
				if(this.getPage()%5 != 0) {
					curBlock++;
				}
				
				//4. Block번호로 시작 번호 끝번호 계산
				Long start = (curBlock-1)*5+1;
				Long end= curBlock*5;
				
				this.setStart(start);
				this.setEnd(end);
				
				//5. curBlock이 마지막 블럭이면
//				/*
//				 * if(totalBlock == curBlock) { this.setEnd(totalPage); this.setEndcheck(true);
//				 */
//				}
				
				
				
		}
		
		
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
			//페이지 번호를 안전하게 반환하는 로직
			
			 // 1. page가 null이거나 1보다 작으면 기본값 1로 설정
			if(this.page==null || this.page<1) {
					//두 조건 중 하나라도 참이면 1 페이지로 기본 설정
					this.page=1L;
			}
			return page; //안전한 페이지 번호를 반환
		}
		public void setPage(Long page) {
			this.page = page;
		}
		public Long getPerPage() {
			if(this.perPage== null) {
				this.perPage=20L;
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
		public boolean isEndCheck() {
			return endCheck;
		}
		public void setEndCheck(boolean endCheck) {
			this.endCheck = endCheck;   
		}

		
		
		
}
