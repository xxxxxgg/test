package com.study.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.study.board.domain.BoardVO;
import com.study.board.framework.paging.PageCriteria;
import com.study.board.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
//Root-Context 참조하기
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDAOTest {
	@Inject
	private BoardDAO dao;
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	//@Test
	public void testFindBoardByID() throws Exception {
		BoardVO vo = dao.findBoardByID(1);
		System.out.println(vo);
	}

	//@Test
	public void testFindAllBoard() throws Exception {
		List<BoardVO> listBoard = dao.findAllBoard();
		for (BoardVO vo : listBoard)
			System.out.println(vo);
	}
	
	//@Test
	public void testFindListOfBoardPerPage() throws Exception {
		PageCriteria cri = new PageCriteria();
		cri.setPageNum(5);
		cri.setPageSize(10);
		dao.findBoardPerPage(cri);
	}
	
	//@Test
	public void testCreateBoard() throws Exception {
		BoardVO vo = new BoardVO();
		vo.setTitle("title");
		vo.setContent("content");
		vo.setWriter("writer");
		dao.createBoard(vo);
	}
	
	/**
	 * SQL Batch process
	 * @throws Exception
	 */
	//@Test
	public void testCreateBoardByBatch() throws Exception {
		List<BoardVO> listBoard = new ArrayList<>();
		for (int h = 0; h < 1000; h++) {
			for (int i = 0; i < 1000; i++) {
				BoardVO vo = new BoardVO();
				vo.setTitle("title" + i);
				vo.setContent("content" + i);
				vo.setWriter("writer" + i);
				listBoard.add(vo);
			}
			
			Map<String, Object>paramMap = new HashMap<String, Object>();
			paramMap.put("list", listBoard);
			dao.createBoardByBatch(paramMap);
		}
	}

	//@Test
	public void testUpdateBoard() throws Exception {
		BoardVO vo = dao.findBoardByID(1);
		vo.setContent("content      dfdsfgdfs");
		dao.updateBoard(vo);
	}
	
	//@Test
	public void testDeleteBoard() throws Exception {
		dao.deleteBoard(1);
	}
	
	//@Test
	public void testGetBoardCount() throws Exception {
		PageCriteria pcr = new PageCriteria();
		dao.getBoardCount(pcr);
	}
	
	@Test
	public void testURI() throws Exception {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.path("/board/read")
				.queryParam("bno", 12)
				.queryParam("pageNum" ,20)
				.build();
		
		logger.info("/board/read?bno=12&pageNum=20");
		logger.info(uriComponents.toString());
	}
}
