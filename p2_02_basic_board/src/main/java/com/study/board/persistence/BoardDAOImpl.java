package com.study.board.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.study.board.domain.BoardVO;
import com.study.board.framework.paging.PageCriteria;
import com.study.board.framework.paging.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	@Inject
	private SqlSession sqlSession;

	private static final String namespace = "p2_02_basic_board.mapper.BoardMapper";

	@Override
	public BoardVO findBoardByID(Integer bno) throws Exception {
		BoardVO vo = sqlSession.selectOne(namespace + ".findBoardByID", bno);
		return vo;
	}

	@Override
	public List<BoardVO> findAllBoard() throws Exception {
		List<BoardVO> ret = sqlSession.selectList(namespace + ".findAllBoard");
		return ret;
	}

	@Override
	public List<BoardVO> findBoardPerPage(PageCriteria pcr) throws Exception {
		List<BoardVO> ret = sqlSession.selectList(namespace + ".findBoardPerPage", pcr);
		return ret;
	}
	@Override
	public List<BoardVO> findBoardPerPageBySearch(SearchCriteria pcr) throws Exception {
		return sqlSession.selectList(namespace + ".findBoardPerPageBySearch", pcr);
	}
	@Override
	public int findCountOfBoardBySearch(SearchCriteria pcr) throws Exception {
		return sqlSession.selectOne(namespace + ".findCountOfBoardBySearch", pcr);
	}

	@Override
	public void createBoard(BoardVO obj) throws Exception {
		sqlSession.insert(namespace + ".createBoard", obj);
	}

	@Override
	public void createBoardByBatch(Map<String, Object> map) {
		sqlSession.insert(namespace + ".createBoardByBatch", map);
	}

	@Override
	public void updateBoard(BoardVO obj) throws Exception {
		sqlSession.update(namespace + ".updateBoard", obj);
	}

	@Override
	public void deleteBoard(Integer bno) throws Exception {
		sqlSession.delete(namespace + ".deleteBoard", bno);

	}

	@Override
	public void batchDeleteBoard(Integer[] arrbno) {
		List<Integer> listDeleteTarget = new ArrayList<>();
		Map<String, List<Integer>> paramMap = new HashMap<>();
		
		for (int i = 0; i < arrbno.length; i++) {
			listDeleteTarget.add(arrbno[i]);
		}
		paramMap.put("arrbno", listDeleteTarget);

		sqlSession.delete(namespace + ".batchDeleteBoard", paramMap);
	}

	@Override
	public int getBoardCount(PageCriteria pcr) throws Exception {
		return sqlSession.selectOne(namespace + ".getBoardCount", pcr);
	}



}
