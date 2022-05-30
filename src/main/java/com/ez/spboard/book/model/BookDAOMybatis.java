package com.ez.spboard.book.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAOMybatis implements BookDAO {
	private static final Logger logger
		= LoggerFactory.getLogger(BookDAOMybatis.class);
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String namespace="config.mybatis.mapper.oracle.book.";
	
	public BookDAOMybatis() {
		logger.info("생성자 호출 : BookDAOMybatis");
	}
	
	public int insertBook(BookDTO dto) {
		int cnt = sqlSession.insert(namespace + "insertBook", dto);
		return cnt;
	}
}
