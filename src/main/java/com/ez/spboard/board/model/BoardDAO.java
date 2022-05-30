package com.ez.spboard.board.model;

import java.util.List;

import com.ez.spboard.common.SearchVO;

public interface BoardDAO {
	
	public int insertBoard(BoardVO vo);
	int getTotalRecord(SearchVO searchVo);
	public List<BoardVO> selectAll(SearchVO searchVo);
	public BoardVO selectByNo(int no);
	public int updateCount(int no);
	
}
