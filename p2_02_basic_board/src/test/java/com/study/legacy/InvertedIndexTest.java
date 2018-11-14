package com.study.legacy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.board.domain.BoardVO;
import com.study.board.framework.paging.PageCriteria;
import com.study.board.persistence.BoardDAO;
import com.study.legacy.domain.AppearanceVO;
import com.study.legacy.domain.LexiconVO;
import com.study.legacy.persistence.AppearanceDAO;
import com.study.legacy.persistence.LexiconDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class InvertedIndexTest {
	@Inject
	private LexiconDAO ldao;
	@Inject
	private AppearanceDAO adao;
	@Inject
	private BoardDAO bdao;

	private static final int PAGE_SIZE = 10000;

	// @Test
	public void testCreateLexiconByBatch() throws Exception {
		List<LexiconVO> listLexicon = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			LexiconVO vo = new LexiconVO();
			vo.setLexicon("title" + (char) ('a' + i));
			listLexicon.add(vo);
		}

		Map<String, List<LexiconVO>> paramMap = new HashMap<>();
		paramMap.put("list", listLexicon);
		ldao.createLexiconByBatch(paramMap);
	}

	// @Test
	public void testCreateAppearanceByBatch() throws Exception {
		List<AppearanceVO> listAppearance = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			AppearanceVO.DescriminaterType descriminater = AppearanceVO.DescriminaterType.Board;
			String targetId = "" + i;
			int lexiconId = i;
			int cnt = 1;
			AppearanceVO vo = new AppearanceVO(descriminater.getTargetTbl(), targetId, lexiconId, cnt);

			listAppearance.add(vo);
		}

		Map<String, List<AppearanceVO>> paramMap = new HashMap<>();
		paramMap.put("list", listAppearance);
		adao.createAppearanceByBatch(paramMap);
	}

	@Test
	public void testCreateIIByBatch() throws Exception {
		List<BoardVO> listvo = new ArrayList<>();
		List<LexiconVO> listLexicon = new ArrayList<>();
		PageCriteria cri = new PageCriteria();
		int curPage = 1;

		cri.setPageSize1(PAGE_SIZE);

		do {
			listvo = bdao.findBoardPerPage(cri);
			Map<String, List<BoardVO>> mapLexBoard = extractLexicon(listvo);
			Map<String, List<String>> mapLex = new HashMap<>();
			List<String> listLexStr = new ArrayList<>();
			listLexStr.addAll(mapLexBoard.keySet());
			mapLex.put("list", listLexStr);
			List<LexiconVO> listExistingLex = ldao.findLexicon(mapLex);

			Map<String, List<LexiconVO>> mapLexvo = new HashMap<>();
			Map<String, List<AppearanceVO>> mapNewLexApp = new HashMap<>();
			mapNewLexApp.put("list", new ArrayList<>());
			Map<String, List<AppearanceVO>> mapOldLexApp = new HashMap<>();
			mapOldLexApp.put("list", new ArrayList<>());

			for (String lex : listLexStr) {
				boolean founded = false;
				LexiconVO existingLex = null;
				if (!listExistingLex.isEmpty()) { 
					for (LexiconVO existingLex4Loop : listExistingLex) {
						if (existingLex4Loop.getLexicon().equals(lex)) {
							founded = true;
							existingLex = existingLex4Loop;
							break;
						}
					}
				}
				if (founded) { // 기존 것은 cnt 증가
					for (BoardVO board : mapLexBoard.get(existingLex.getLexicon())) {
						AppearanceVO avo = new AppearanceVO(AppearanceVO.DescriminaterType.Board.getTargetTbl(),
								String.valueOf(board.getBno()), existingLex.getId(), mapLexBoard.get("list").size());
						mapOldLexApp.get("list").add(avo);
					}
				} else { // 새로운 것은 어휘 등록
					LexiconVO newLex = new LexiconVO();
					newLex.setLexicon(lex);
					
					mapLexvo.get("list").add(newLex);

					for (BoardVO board : mapLexBoard.get(lex)) {
						AppearanceVO avo = new AppearanceVO(AppearanceVO.DescriminaterType.Board.getTargetTbl(),
								String.valueOf(board.getBno()), lex, mapLexBoard.get(lex).size());
						mapNewLexApp.get("list").add(avo);
					}
				}
			}
			ldao.createLexiconByBatch(mapLexvo);
			adao.createAppearanceByBatch(mapNewLexApp);
			adao.createAppearanceByBatch(mapOldLexApp);

			curPage++;
			cri.setPageNum(curPage);
		} while (listvo.size() == PAGE_SIZE);
	}

	private void mergeLexBoard(Map<String, List<BoardVO>> totalmapLexBoard, Map<String, List<BoardVO>> mapLexBoard) {
		for (String aKey : mapLexBoard.keySet()) {
			if (totalmapLexBoard.containsKey(aKey)) {
				totalmapLexBoard.get(aKey).addAll(mapLexBoard.get(aKey));
			} else {
				totalmapLexBoard.put(aKey, mapLexBoard.get(aKey));
			}
		}
	}

	private Map<String, List<BoardVO>> extractLexicon(List<BoardVO> listvo) {
		Map<String, List<BoardVO>> ret = new HashMap<>();

		for (BoardVO board : listvo) {
			List<String> listLexCandi = board.extractLexicon();
			Set<String> setLexBoard = tokenize(listLexCandi);
			for (String lex : setLexBoard) {
				if (ret.containsKey(lex)) {
					ret.get(lex).add(board);
				} else {
					List<BoardVO> val = new ArrayList<>();
					val.add(board);
					ret.put(lex, val);
				}
			}
		}

		return ret;
	}

	private Set<String> tokenize(List<String> listLexCandi) {
		Set<String> ret = new HashSet();

		for (String lex : listLexCandi) {
			String[] arrToken = lex.split(" ");
			ret.addAll(Arrays.asList(arrToken));
		}

		return ret;
	}
}
