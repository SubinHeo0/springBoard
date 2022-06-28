package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class BoardDAOTest {
	
	// DAO 객체 필요 => 객체 주입
	@Inject
	private BoardDAO dao;
	
	//@Test
	public void 글쓰기동작() {
		BoardVO vo = new BoardVO();
		vo.setTitle("공지사항 입니다");
		vo.setContent("주말은 휴무입니다");
		vo.setWriter("관리자");
		
		dao.create(vo);
	}
	
	//@Test
	public void 페이징처리_호출() {
		List<BoardVO> boardList = dao.listPage();
		
		for(BoardVO vo : boardList) {
			System.out.println(vo.getBno()+" : "+vo.getTitle());
		}
	}
	
	//@Test
	public void 페이징처리_호출2() {
		List<BoardVO> boardList = dao.listPage(3, 10);
		
		for(BoardVO vo : boardList) {
			System.out.println(vo.getBno()+" : "+vo.getTitle());
		}
	}
	
	@Test
	public void 페이징처리_호출3() {
		Criteria cri = new Criteria();
		cri.setPage(3);
		cri.setPerPageNum(5);
		
		List<BoardVO> boardList = dao.listPage(cri);
		
		
		for(BoardVO vo : boardList) {
			System.out.println(vo.getBno()+" : "+vo.getTitle());
		}
	}
	
	
	
	
}
