package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	
	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);

	
	// 디비연결 및 mapper 연결처리 객체
	@Inject
	private SqlSession sqlSession;
	
	static final String NAMESPACE = "com.itwillbs.mapper.BoardMapper";

	@Override
	public void create(BoardVO vo) {
		log.info(" 정보 전달받아서 mapper 호출 ");
		// 정보 전달받아서 mapper를 거쳐서 DB에 저장
		sqlSession.insert(NAMESPACE+".createBoard", vo);
		
		log.info(" mapper에서 처리 후 이동 ");
		
	}

	@Override
	public List<BoardVO> listAll() {
		log.info(" listAll() 호출 ");
		
		// mapper 해당 sql 호출
		List<BoardVO> boardList = sqlSession.selectList(NAMESPACE+".listAll");
		
		return boardList;
		// return sqlSession.selectList(NAMESPACE+".listAll");
		
	}

	@Override
	public BoardVO getBoard(Integer bno) {
		log.info(" getBoard() 호출 ");
		
		// mapper에 있는 sql 호출
		BoardVO vo = sqlSession.selectOne(NAMESPACE+".getBoard", bno);
		return vo;
	}

	@Override
	public void updateBoard(BoardVO vo) {
		 // mapper - sql호출
		 sqlSession.update(NAMESPACE+".updateBoard", vo);
	}

	@Override
	public void removeBoard(Integer bno) {

		sqlSession.delete(NAMESPACE+".deleteBoard", bno);
		
	}

	@Override
	public void updateBoardCnt(Integer bno) {
		sqlSession.update(NAMESPACE+".updateBoardCnt", bno);
	}

	@Override
	public List<BoardVO> listPage() {
		log.info("listPage() 호출");
		return sqlSession.selectList(NAMESPACE+".listPage");
	}

	@Override
	public List<BoardVO> listPage(int page, int size) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		
		if(page <= 0) {
			page = 1;
		}
		
		if(size <= 0) {
			size = 10;
		}
		
		page = (page - 1) * size;
		
		param.put("pageStart", page);
		param.put("perPageNum", size);
		
		return sqlSession.selectList(NAMESPACE+".listCri",param);
	}

	@Override
	public List<BoardVO> listPage(Criteria cri) {
		return sqlSession.selectList(NAMESPACE+".listCri", cri);
	}
	
	
	
	

}
