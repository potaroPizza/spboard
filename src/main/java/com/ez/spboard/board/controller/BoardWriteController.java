package com.ez.spboard.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ez.spboard.board.model.BoardService;
import com.ez.spboard.board.model.BoardVO;

@Controller
public class BoardWriteController {
	private static final Logger logger
		= LoggerFactory.getLogger(BoardWriteController.class);
	
	@Autowired
	private BoardService boardService; 
	
	@RequestMapping(value = "/board/write.do", method = RequestMethod.GET)
	public String write_get() {
		logger.info("글 등록 페이지");
		
		return "/board/write";
		//=> http://localhost:9090/spboard/board/write.do
	}
	
	@RequestMapping(value = "/board/write.do", method = RequestMethod.POST)
	public String write_post(@ModelAttribute BoardVO vo) {
		logger.info("글 등록 처리, 매개변수vo = {}", vo);
		
		int cnt = boardService.insertBoard(vo);
		logger.info("글 등록 처리 결과, cnt = {}", cnt);
		
		return "redirect:/board/list.do";
		// do => controller => service인터페이스 => 구현체service의 메소드 호출  
		// =>dao인터페이스 => 구현체dao의 메소드호출
	}
}




















