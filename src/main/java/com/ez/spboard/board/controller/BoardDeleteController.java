package com.ez.spboard.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ez.spboard.board.model.BoardService;
import com.ez.spboard.board.model.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardDeleteController {
	private static final Logger logger
		= LoggerFactory.getLogger(BoardDeleteController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public String delete_get(@RequestParam(defaultValue = "0") int no,
			Model model) {
		logger.info("글 삭제 페이지, 파라미터 no={}", no);
		
		if(no == 0) {
			model.addAttribute("msg", "잘못된 url입니다");
			model.addAttribute("url", "/board/list.do");
			return "/common/message";
		}
		
		return "/board/delete";
	}
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public String delete_post(@ModelAttribute BoardVO vo,
			Model model) {
		logger.info("글 삭제 처리, 파라미터 no={}, pwd={}", vo.getNo(), vo.getPwd());
		
		//비밀번호 체크
		String msg = "비밀번호 체크 실패", url = "/board/delete.do?no="+vo.getNo();
		if(boardService.checkPwd(vo.getNo(), vo.getPwd())) {
			// 비밀번호가 일치하는 경우
			// 삭제처리
			int cnt = boardService.deleteBoard(vo.getNo());
			logger.info("글 삭제 처리 결과, no = {}", vo.getNo());
			if(cnt > 0) {
				msg = "글 삭제를 성공했습니다.";
				url = "/board/list.do";
			} else {
				msg = "글 삭제를 실패했습니다.";
			}
			
		} else {
			msg = "비밀번호가 일치하지 않습니다.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "/common/message";
	}
	
}
