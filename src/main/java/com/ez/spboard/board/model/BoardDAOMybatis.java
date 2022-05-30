package com.ez.spboard.board.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOMybatis implements BoardDAO {
	private static final Logger logger 
		= LoggerFactory.getLogger(BoardDAOMybatis.class);
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String namespace = "config.mybatis.mapper.oracle.board.";

	public BoardDAOMybatis() {
		super();
		logger.info("생성자 호출 : BoardDAOMybatis");
	}

	public int insertBoard(BoardVO vo) {
		int cnt = sqlSession.insert(namespace + "insertBoard", vo);
		return cnt;
	}
/*	
	public List<BoardVO> selectAll() {
		
	}
	
	public BoardVO selectByNo(int no) {
		
	}
	
	public int updateBoard(BoardVO vo) {
		
	} 
	
	public int deleteBoard(BoardVO vo) {
		
	}
	
	public int updateCount(int no) {
		
	}
	
	public List<BoardVO> selectAll(String condition, String keyword) {
		
	}
	
	public List<BoardVO> selectMainNotice() {
		
	}
	
	public boolean checkPwd(int no, String pwd) {
	     
	      
	}
*/
	
}
