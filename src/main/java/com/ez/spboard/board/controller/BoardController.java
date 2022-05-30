package com.ez.spboard.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ez.spboard.board.model.BoardService;

@Controller
public class BoardController {
	private static final Logger logger
		= LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService; 
	
	@RequestMapping("/board/countUpdate.do")
	public String countUpdate(@RequestParam(defaultValue = "0") int no, Model model) {
		logger.info("조회수 증가, 파라미터 = {}", no);
		
		if(no == 0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/board/list.do");
			return "/common/message";
		}
		
		int cnt = boardService.updateCount(no);
		logger.info("조회수 증가 결과, cnt = {}", cnt);
		
		return "redirect:/board/detail.do?no="+no;
	}
}
