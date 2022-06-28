package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;

public interface BoardDAO {
	
	// 글쓰기
	public void create(BoardVO vo);
	
	// 글 리스트 (select - list)
	public List<BoardVO> listAll();
	
	// 글 내용 (select - read)
	public BoardVO getBoard(Integer bno);
	
	// 글 수정 (update)
	public void updateBoard(BoardVO vo);
	
	// 글 삭제 (delete)
	public void removeBoard(Integer bno);
	
	// 조회수 증가 (update)
	public void updateBoardCnt(Integer bno);
	
	// 글 페이징처리
	public List<BoardVO> listPage();
	public List<BoardVO> listPage(int page, int size);
	public List<BoardVO> listPage(Criteria cri);
	
	
}
