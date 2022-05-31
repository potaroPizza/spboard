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

	public int insertBoard(BoardVO vo) {
		int cnt = sqlSession.insert(namespace + "insertBoard", vo);
		return cnt;
	}
	
	public List<BoardVO> selectAll(SearchVO searchVo) {
		List<BoardVO> list = sqlSession.selectList(namespace + "selectAll", searchVo);
		return list;
	}

	@Override
	public int getTotalRecord(SearchVO searchVo) {
		int totalRecord = sqlSession.selectOne(namespace + "getTotalRecord", searchVo);
		return totalRecord;
	}
	
	public BoardVO selectByNo(int no) {
		BoardVO vo = sqlSession.selectOne(namespace + "selectByNo", no); 
		return vo;
	}
	
	public int updateCount(int no) {
		int cnt = sqlSession.update(namespace + "updateCount", no); 
		return cnt;
	}
	
	public int updateBoard(BoardVO vo) {
		int cnt = sqlSession.update(namespace + "updateBoard", vo);
		return cnt;
	}

	@Override
	public String selectPwd(int no) {
		String dbPwd = sqlSession.selectOne(namespace + "selectPwd", no);
		return dbPwd;
	} 
	
	public int deleteBoard(int no) {
		int cnt = sqlSession.delete(namespace + "deleteBoard", no);
		return cnt;
	}
	
	/*	
	
	
	
	
	
	public List<BoardVO> selectMainNotice() {
		
	}
	
*/
	
}
