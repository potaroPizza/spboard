package com.ez.spboard.book.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
	private static final Logger logger
		= LoggerFactory.getLogger(BookServiceImpl.class);
	
	@Autowired
	private BookDAO bookDAO;
	
	public BookServiceImpl() {
		logger.info("생성자 호출 : BookServiceImpl");
	}

	@Override
	public int insertBook(BookDTO dto) {
		return bookDAO.insertBook(dto);
	}

}
