package com.itwillbs.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

@RestController
public class BoardRESTController {
	
	private static final Logger log = LoggerFactory.getLogger(BoardRESTController.class);
	
	@Inject
	private BoardService service;
	
	// http://localhost:8088/board/listCri
	
	
	// http://localhost:8088/count
	
	
	@RequestMapping(value = "/count")
	public String getTotalCntREST() {
		
		log.info(" getTotalCntREST() 호출 ");
		
		return ""+service.totalCnt();
	}
	
	// http://localhost:8088/count2/100
	@RequestMapping(value = "/count2/{num}")
	public BoardVO getTotalCntREST2(@PathVariable("num") int num) {
		
		log.info(" getTotalCntREST2() 호출 ");
		log.info(num+"");
		
		return service.readBoard(num);
	}
	
}
