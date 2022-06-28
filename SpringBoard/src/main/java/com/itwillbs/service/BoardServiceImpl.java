package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	// DAO 객체 주입
	@Inject
	private BoardDAO dao;

	@Override
	public void boardCreate(BoardVO vo) {
		dao.create(vo);
	}

	@Override
	public List<BoardVO> getBoardListAll() {
		List<BoardVO> boardList = dao.listAll();
		
		// return dao.listAll();
		return boardList;
	}

	@Override
	public BoardVO readBoard(Integer bno) {
		BoardVO vo = dao.getBoard(bno);
		
		return vo;
	}

	@Override
	public void updateBoard(BoardVO vo) {
		dao.updateBoard(vo);
	}

	@Override
	public void deleteBoard(Integer bno) {
		dao.removeBoard(bno);
	}

	@Override
	public void updateBoardCount(Integer bno) {
		dao.updateBoardCnt(bno);
	}

	@Override
	public List<BoardVO> boardListCri(Criteria cri) {
		return dao.listPage(cri);
	}

	@Override
	public Integer totalCnt() {
		return dao.getTotalCnt();
	}
	
	
	
	
	

}
