
package com.study.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.study.board.domain.BoardVO;
import com.study.board.framework.paging.PageCriteria;
import com.study.board.framework.paging.SearchCriteria;
import com.study.board.persistence.BoardDAO;

@Service
public class BoardServiceImple implements BoardService {
	@Inject
	private BoardDAO dao;
	
	@Override
	public void createBoard(BoardVO obj) throws Exception {
		dao.createBoard(obj);
	}

	@Override
	public BoardVO findBoardByID(Integer bno) throws Exception {
		BoardVO ret = dao.findBoardByID(bno);
		return ret;
	}

	@Override
	public List<BoardVO> findAllBoard() throws Exception {
		List<BoardVO> ret = dao.findAllBoard();
		return ret;
	}
	
	@Override
	public List<BoardVO> findListOfBoardPerPage(PageCriteria pcr) throws Exception {
		List<BoardVO> ret = dao.findBoardPerPage(pcr);
		return ret;
	}
	@Override
	public List<BoardVO> findBoardPerPageBySearch(SearchCriteria pcr) throws Exception {
		return dao.findBoardPerPageBySearch(pcr);
	}
	
	@Override
	public int findCountOfBoardBySearch(SearchCriteria pcr) throws Exception {
		return dao.findCountOfBoardBySearch(pcr);
	}
	
	@Override
	public void updateBoard(BoardVO obj) throws Exception {
		dao.updateBoard(obj);
	}

	@Override
	public void deleteBoard(Integer bno) throws Exception {
		dao.deleteBoard(bno);
	}
	@Override
	public void batchDeleteBoard(Integer[] arrbno) throws Exception {
		dao.batchDeleteBoard(arrbno);
	}


	@Override
	public int getBoardCount(PageCriteria pcr) throws Exception {
		return dao.getBoardCount(pcr);
	}



}
