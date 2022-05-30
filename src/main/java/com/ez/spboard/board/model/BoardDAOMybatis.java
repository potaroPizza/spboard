package com.ez.spboard.board.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ez.spboard.common.SearchVO;

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
	
	@Override
	public int insertBoard(BoardVO vo) {
		int cnt = sqlSession.insert(namespace + "insertBoard", vo);
		return cnt;
	}
	
	@Override
	public List<BoardVO> selectAll(SearchVO searchVo) {
		List<BoardVO> list = sqlSession.selectList(namespace + "selectAll", searchVo);
		return list;
	}

	@Override
	public int getTotalRecord(SearchVO searchVo) {
		int totalRecord = sqlSession.selectOne(namespace + "getTotalRecord", searchVo);
		return totalRecord;
	}
	
	@Override
	public BoardVO selectByNo(int no) {
		BoardVO vo = sqlSession.selectOne(namespace + "selectByNo", no); 
		return vo;
	}
	
	@Override
	public int updateCount(int no) {
		int cnt = sqlSession.update(namespace + "getTotalRecord", no); 
		return cnt;
	}
	
	/*	
	
	public int updateBoard(BoardVO vo) {
		
	} 
	
	public int deleteBoard(BoardVO vo) {
		
	}
	
	
	public List<BoardVO> selectMainNotice() {
		
	}
	
	public boolean checkPwd(int no, String pwd) {
	     
	      
	}
*/
	
}
