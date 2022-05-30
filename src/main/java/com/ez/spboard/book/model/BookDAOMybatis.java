package com.ez.spboard.book.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ez.spboard.common.SearchVO;

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
	
	@Override
	public List<BookDTO> selectAll(SearchVO searchVo) {
		List<BookDTO> list = sqlSession.selectList(namespace + "selectAll", searchVo);
		return list;
	}

	@Override
	public int getTotalRecord(SearchVO searchVo) {
		int totalRecord = sqlSession.selectOne(namespace + "getTotalRecord", searchVo);
		return totalRecord;
	}
	
	@Override
	public BookDTO selectByNo(int no) {
		BookDTO dto = sqlSession.selectOne(namespace + "selectByNo", no); 
		return dto;
	}
	
	@Override
	public int updateCount(int no) {
		int cnt = sqlSession.update(namespace + "getTotalRecord", no); 
		return cnt;
	}
}
