package com.itwillbs.domain;

public class PageMarker {
	// 페이징처리에 필요한 모든 정보를 저장하는 객체
	
	// 상단부 페이징 처리
	private Criteria cri;	// page, perPageNum
	
	// 하단부 페이징 처리
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum = 10;	// 페이지 블럭의 수

	

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		calData();
	}
	
	public void calData() {
		System.out.println("---------페이징처리에 필요한 정보 계산----------");
		
		endPage
			= (int) (Math.ceil(cri.getPage()/(double)displayPageNum) * displayPageNum);
		
		startPage = (endPage - displayPageNum) + 1;
		
		int tmpEndPage = (int)( Math.ceil(totalCount/(double) cri.getPerPageNum()));
		
		if(endPage > tmpEndPage) {
			endPage = tmpEndPage;
		}
		
		prev = startPage == 1? false : true;
		
		next = endPage * cri.getPerPageNum() >= totalCount? false : true;
		
		System.out.println(" 하단 페이징처리에 필요한 정보 계산 완료! ");
				
		
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

	public int getTotalCount() {
		return totalCount;
	}

	
	@Override
	public String toString() {
		return "PageMarker [cri=" + cri + ", totalCount=" + totalCount + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prev=" + prev + ", next=" + next + ", displayPageNum=" + displayPageNum + "]";
	}
	
	
	
	
	
}
