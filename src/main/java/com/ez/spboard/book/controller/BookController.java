package com.ez.spboard.book.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ez.spboard.book.model.BookDTO;
import com.ez.spboard.book.model.BookService;
import com.ez.spboard.common.PaginationInfo;
import com.ez.spboard.common.SearchVO;
import com.ez.spboard.common.Utility;

@Controller
public class BookController {
	private static final Logger logger
		= LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/book/bookWrite.do", method = RequestMethod.GET)
	public String write_get() {
		logger.info("책 등록 페이지");
		
		return "/book/bookWrite";
		//=> http://localhost:9090/spboard/book/bookWrite.do
	}
	
	@RequestMapping(value = "/book/bookWrite.do", method = RequestMethod.POST)
	public String write_post(@ModelAttribute BookDTO bookDto) {
		logger.info("책 등록 처리, 매개변수 bookDto = {}", bookDto);
		
		int cnt = bookService.insertBook(bookDto);
		logger.info("책 등록 처리 결과, cnt = {}", cnt);
		
		return "redirect:/book/list.do";
	}
	
	@RequestMapping("/book/bookList.do")
	public String list(@ModelAttribute SearchVO searchVo, Model model) {
		logger.info("책 목록 페이지, 파라미터 searchVo = {}", searchVo);
		
		//[1] PaginationInfo 생성
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setRecordCountPerPage(Utility.RECORD_COUNT);
		pagingInfo.setBlockSize(Utility.BLOCKSIZE);
		pagingInfo.setCurrentPage(searchVo.getCurrentPage());
		
		//[2] searchVo에 페이징 처리 관련 변수의 값 셋팅
		searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		searchVo.setRecordCountPerPage(Utility.RECORD_COUNT);
		
		List<BookDTO> list = bookService.selectAll(searchVo);
		logger.info("책 목록 조회 결과, list.size={}", list.size());
		
		//totalRecord개수 구하기
		int totalRecord = bookService.getTotalRecord(searchVo);
		logger.info("책 목록 totalRecord={}", totalRecord);
		
		// 이거 빼먹으면, 다른거 계산이 안됨
		pagingInfo.setTotalRecord(totalRecord);
		
		// request객체, 모델앤뷰객체에 저장하는것과 똑같은 구조
		model.addAttribute("list", list);
		model.addAttribute("pagingInfo", pagingInfo);
		
		return "/book/bookList";
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
