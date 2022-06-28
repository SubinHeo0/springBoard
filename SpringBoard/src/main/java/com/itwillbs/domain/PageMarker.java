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
}
