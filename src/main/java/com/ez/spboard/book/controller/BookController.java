package com.ez.spboard.book.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ez.spboard.book.model.BookDTO;
import com.ez.spboard.book.model.BookService;

@Controller
public class BookController {
	private static final Logger logger
		= LoggerFactory.getLogger(BookController.class);

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
}
