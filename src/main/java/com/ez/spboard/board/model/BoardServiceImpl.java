package com.ez.spboard.board.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ez.spboard.common.SearchVO;

@Service
public class BoardServiceImpl implements BoardService {
	private static final Logger logger
		= LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Autowired
	private BoardDAO boardDao;
	
	public BoardServiceImpl() {
		logger.info("생성자 호출 : BoardServiceImpl");
	}
	
	@Override
	public int insertBoard(BoardVO vo) {
		return boardDao.insertBoard(vo);
	}
	
	@Override
	public int getTotalRecord(SearchVO searchVo) {
		return boardDao.getTotalRecord(searchVo);
	}
	
	@Override
	public List<BoardVO> selectAll(SearchVO searchVo) {
		return boardDao.selectAll(searchVo);
	}
	
	@Override
	public BoardVO selectByNo(int no) {
		return boardDao.selectByNo(no);
	}
	
	@Override
	public int updateCount(int no) {
		return boardDao.updateCount(no);
	}
	
	
/*
	public int updateBoard(BoardVO vo) {
		return boardDao.updateBoard(vo);
	}
	public List<BoardVO> selectMainNotice() {
		return boardDao.selectMainNotice();
	}
	
	public List<BoardVO> selectAll() {
		return boardDao.selectAll();
	}
	
	public List<BoardVO> selectAll(String condition, String keyword) {
		return boardDao.selectAll(condition, keyword);
	}
	
	
	public int deleteBoard(BoardVO vo) {
		return boardDao.deleteBoard(vo);
	}
	
	public int updateCount(int no) {
		return boardDao.updateCount(no);
	}
	
	public boolean checkPwd(int no, String pwd) {
		return boardDao.checkPwd(no, pwd);
	}
*/

	
}
