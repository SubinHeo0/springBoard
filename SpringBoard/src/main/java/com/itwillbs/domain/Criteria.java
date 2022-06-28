package com.itwillbs.domain;

public class Criteria {
	// 페이징 처리에 필요한 정보를 저장하는 객체
	
	private int page;	// 페이지 시작번호
	private int perPageNum;	// 페이지 크기
	
	public Criteria() {
		System.out.println("-------------------------기본생성자 (1페이지 10개씩)-----------------------");
		this.page = 1;
		this.perPageNum = 10;
	}
	
	
	// alt shift s + r
	public void setPage(int page) {
		System.out.println("-----------------------setPage(page)"+page+"페이지--------------------------");
		if(page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	public void setPerPageNum(int perPageNum) {
		System.out.println("-----------------------setPerPageNum(int perPageNum)"+perPageNum+"개--------------------------");
		if(perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		
		this.perPageNum = perPageNum;
	}
	
	// mapper에서 사용될 메서드
	public int getPageStart() {
		System.out.println("------------------------getPageStart()------------------");
		return (this.page - 1) * perPageNum;
	}

	public int getPerPageNum() {
		System.out.println("-------------------getPerPageNum()----------------------");
		return perPageNum;
	}

	public int getPage() {
		return page;
	}
	
	// alt shift s + s
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
	

	
	
	
	

}
