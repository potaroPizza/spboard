package com.ez.spboard.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ez.spboard.board.model.BoardService;
import com.ez.spboard.board.model.BoardVO;
import com.ez.spboard.common.PaginationInfo;
import com.ez.spboard.common.SearchVO;
import com.ez.spboard.common.Utility;

@Controller
public class BoardListController {
	private static final Logger logger
		=LoggerFactory.getLogger(BoardListController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/board/list.do")
	public String list(@ModelAttribute SearchVO searchVo, Model model) {
		logger.info("글 목록, 파라미터 searchVo={}", searchVo);
		
		//[1] PaginationInfo 생성
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setBlockSize(Utility.BLOCKSIZE);
		pagingInfo.setRecordCountPerPage(Utility.RECORD_COUNT);
		pagingInfo.setCurrentPage(searchVo.getCurrentPage());
		
		//[2] searchVo에 페이징 처리 관련 변수의 값 셋팅
		searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		searchVo.setRecordCountPerPage(Utility.RECORD_COUNT);

		List<BoardVO> list=boardService.selectAll(searchVo);
		logger.info("글 목록 조회 결과, list.size={}", list.size());
		
		//totalRecord개수 구하기
		int totalRecord=boardService.getTotalRecord(searchVo);
		logger.info("글목록 totalRecord={}", totalRecord);
		
		// 이거 빼먹으면, 다른거 계산이 안됨
		pagingInfo.setTotalRecord(totalRecord);
		
		// request객체, 모델앤뷰객체에 저장하는것과 똑같은 구조
		model.addAttribute("list", list);
		model.addAttribute("pagingInfo", pagingInfo);
		
		return "/board/list";
	}
	
}





