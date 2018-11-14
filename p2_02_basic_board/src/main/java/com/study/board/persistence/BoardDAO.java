package com.study.board.persistence;

import java.util.List;
import java.util.Map;

import com.study.board.domain.BoardVO;
import com.study.board.framework.paging.PageCriteria;
import com.study.board.framework.paging.SearchCriteria;

public interface BoardDAO {
	public BoardVO findBoardByID(Integer bno) throws Exception;
	
	public List<BoardVO> findAllBoard() throws Exception;
	
	public List<BoardVO> findBoardPerPage(PageCriteria pcr) throws Exception;
	
	public List<BoardVO> findBoardPerPageBySearch(SearchCriteria pcr) throws Exception;

	public int findCountOfBoardBySearch(SearchCriteria pcr) throws Exception;
	
	public void createBoard(BoardVO obj) throws Exception;
	
	public void createBoardByBatch(Map<String, Object> map) throws Exception;
	
	public void updateBoard(BoardVO obj) throws Exception;
	
	public void deleteBoard(Integer bno) throws Exception;
	
	public void batchDeleteBoard(Integer[] arrbno);
	
	public int getBoardCount(PageCriteria pcr) throws Exception;
}
