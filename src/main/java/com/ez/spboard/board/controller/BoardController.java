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
public class BoardController {
	private static final Logger logger
		= LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService; 
	
	@RequestMapping("/countUpdate.do")
	public String countUpdate(@RequestParam(defaultValue = "0") int no,
			Model model) {
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
	
	@RequestMapping("/detail.do")
	public String detail(@RequestParam(defaultValue = "0") int no,
			Model model) {
		logger.info("글 상세보기 파라미터 no = {}", no);
		
		if(no == 0) {
			model.addAttribute("msg", "잘못된 url입니다");
			model.addAttribute("url", "/board/list.do");
			return "/common/message";
		}
		
		BoardVO vo = boardService.selectByNo(no);
		logger.info("글 상세보기 결과 vo = {}", vo);
		
		model.addAttribute("vo", vo);
		
		return "/board/detail";
	}
	
	@RequestMapping(value = "/edit.do", method = RequestMethod.GET)
	public String edit_get(@RequestParam(defaultValue = "0") int no,
			Model model) {
		logger.info("글 수정 페이지, 파라미터 no = {}", no);
		
		if(no == 0) {
			model.addAttribute("msg", "잘못된 url입니다");
			model.addAttribute("url", "/board/list.do");
			return "/common/message";
		}
		
		BoardVO vo = boardService.selectByNo(no);
		logger.info("수정할 글 상세보기 결과 vo = {}", vo);
		
		model.addAttribute("vo", vo);
		
		return "/board/edit";
	}
	
	@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
	public String edit_post(@ModelAttribute BoardVO vo,
			Model model) {
		logger.info("글 수정 처리, 파라미터 vo = {}", vo);
		
		//비밀번호 체크
		String msg = "비밀번호 체크 실패", url = "/board/edit.do?no="+vo.getNo();
		if(boardService.checkPwd(vo.getNo(), vo.getPwd())) {
			// 비밀번호가 일치하는 경우
			// 수정 처리
			int cnt = boardService.updateBoard(vo);
			logger.info("글 수정 처리 결과,cnt = {}", cnt);
			
			if(cnt > 0) {
				msg = "글 수정을 성공했습니다.";
				url = "/board/detail.do?no="+vo.getNo();
			} else {
				msg = "글 수정을 실패했습니다.";
			}
			
		} else {
			msg = "비밀번호가 일치하지 않습니다.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "/common/message";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
