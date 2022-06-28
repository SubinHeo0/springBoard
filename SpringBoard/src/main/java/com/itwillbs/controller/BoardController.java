package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.PageMarker;
import com.itwillbs.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	// 서비스 객체 주입
	@Inject
	private BoardService service;
	
	// 글쓰기 - GET
	// http://localhost:8088/board/register
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET() {
		log.info("registerGET()호출 ");
		
	}
	
	// 글쓰기 - POST
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO vo, RedirectAttributes rttr) {
		log.info(" registerPOST() 호출 ");
		// 한글처리 (=>web.xml 필터로 처리)
		// 전달된 정보 저장 (글쓰기 정보)
		log.info(" 글쓰기 정보 : "+vo);
		
		// 서비스 동작 호출
		service.boardCreate(vo);
		log.info(" 글쓰기 완료! ");
		
		// 정보를 전달
		rttr.addFlashAttribute("result", "REGOK");
		
		//return "/board/success";
		return "redirect:/board/listAll";
		
	}
	
	// http://localhost:8088/board/listAll
	// 글 리스트 - GET
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAllGET(Model model, @ModelAttribute("result") String result, HttpSession session) {
		log.info(" listAllGET() 호출 ");
		
		log.info("결과 : "+result);
		
		// 디비에 저장되어 있는 모든 글정보를 가져와서
		// => 서비스 동작
		List<BoardVO> boardList = service.getBoardListAll();
		//log.info(boardList+"");
		
		session.setAttribute("upFlag", "1");	// 0-false, 1-true
		
		// 연결된 뷰페이지에 출력
		model.addAttribute("boardList", boardList);
		model.addAttribute("result", result);
		
	}
	
	// http://localhost:8088/board/read?bno=1
	// 글 본문 보기
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	//public void readGET(@ModelAttribute("bno") int bno) {
	public void readGET(@RequestParam("bno") int bno, Model model, HttpSession session) {
		// @RequestParam => request.getParameter("이름");
		// -> 유사한 동작을 수행(문자열, 숫자 자동형변환)
		log.info(" readGET() 호출 ");
		log.info(" bno : "+bno);
		
		//session.setAttribute("upFlag", "1");	// 0-false, 1-true
		String upFlag = (String)session.getAttribute("upFlag");
		
		if(upFlag.equals("1")) {
			// 글 조회수 +1 증가
			service.updateBoardCount(bno);
			session.setAttribute("upFlag", "0");
		}
		
		
		// 글 번호를 가지고 서비스 - 글정보 가져오기 동작 호출
		BoardVO vo = service.readBoard(bno);
		//log.info(vo+"");
		
		// 가져온 데이터를 연결된 뷰페이지에 출력
		model.addAttribute("vo", vo);
		//model.addAttribute("vo", service.readBoard(bno));
		//model.addAttribute(service.readBoard(bno));
		
	}
	
	// http://localhost:8088/board/modify?bno=14
	// 글 수정하기
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(@RequestParam("bno") int bno, Model model) {
		log.info(" modifyGET() 호출 ");
		log.info(" 수정할 글 번호 : "+bno);
		
		// bno에 해당하는 글정보를 가져와서 처리 (서비스)
		model.addAttribute("uvo", service.readBoard(bno));
	}
	
	// 글 수정하기(수정할 정보를 전달받아서 DB에 수정)
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardVO vo, RedirectAttributes rttr) {
		log.info(" modifyPOST() 호출 ");
		// 전달된 데이터 저장 (수정 데이터)
		// 확인
		//log.info(vo+"");
		
		// 전달받은 객체정보를 사용하여 데이터 수정 (서비스)
		service.updateBoard(vo);
		
		rttr.addFlashAttribute("result", "MODOK");
		
		return "redirect:/board/listCri";
	}
	
	// http://localhost:8088/board/remove
	// 글 삭제
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String removePOST(@RequestParam("bno") int bno, RedirectAttributes rttr) {
		log.info(" removePOST() 호출 ");
		// 전달된 정보 저장(bno)
		//log.info(" bno : "+bno);
		
		// bno를 사용하여 서비스 - 글삭제
		service.deleteBoard(bno);
		//service.deleteB(null);	에러 발생 호출
		
		rttr.addFlashAttribute("result", "DELOK");
		
		// "글 삭제 완료" 메세지 출력, 페이지 이동
		return "redirect:/board/listCri";
	}
	
	
	// http://localhost:8088/board/listCri
	// http://localhost:8088/board/listCri?perPageNum=5
	// http://localhost:8088/board/listCri?page=5
	// http://localhost:8088/board/listCri?perPageNum=5&page=5
	// 페이징처리 - 게시판리스트
	@RequestMapping(value = "/listCri", method = RequestMethod.GET)
	public void listCriGET(Criteria cri, Model model, HttpSession session) throws Exception {
		log.info(" listCriGET() - 페이징 처리 리스트 ");
		
		// 페이징 처리 객체
//		Criteria cri = new Criteria();
//		cri.setPerPageNum(5);
		
		// 서비스 - 페이징처리된 리스트 동작 호출
		// 해당정보를 view 페이지로 전달
		model.addAttribute("boardList", service.boardListCri(cri));
		
		// 하단 페이징처리 정보 전달
		PageMarker pageMarker = new PageMarker();
		pageMarker.setCri(cri);
		pageMarker.setTotalCount(service.totalCnt());
		
		model.addAttribute("pm", pageMarker);
		
		session.setAttribute("upFlag", "1");	// 0-false, 1-true
		
	}
	

}
