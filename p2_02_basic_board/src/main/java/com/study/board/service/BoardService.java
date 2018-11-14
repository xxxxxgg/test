package com.study.board.service;

import java.util.List;

import com.study.board.domain.BoardVO;
import com.study.board.framework.paging.PageCriteria;
import com.study.board.framework.paging.SearchCriteria;

public interface BoardService {
	public static int MAX_PAGE_SIZE = 10;
	public static int DEFAULT_PAGE_SIZE = 100;
	
	public void createBoard(BoardVO obj) throws Exception;
	
	public BoardVO findBoardByID(Integer bno) throws Exception;
	
	public List<BoardVO> findAllBoard() throws Exception;

	public List<BoardVO> findListOfBoardPerPage(PageCriteria pcr) throws Exception;

	public List<BoardVO> findBoardPerPageBySearch(SearchCriteria pcr) throws Exception;
	
	public int findCountOfBoardBySearch(SearchCriteria pcr) throws Exception;
	
	public void updateBoard(BoardVO obj) throws Exception;
	
	public void deleteBoard(Integer bno) throws Exception;
	
	public void batchDeleteBoard(Integer[] arrbno) throws Exception;

	public int getBoardCount(PageCriteria pcr) throws Exception;

	
}
