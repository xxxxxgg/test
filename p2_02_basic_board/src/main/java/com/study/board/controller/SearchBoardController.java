package com.study.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.board.domain.BoardVO;
import com.study.board.framework.paging.PageCriteria;
import com.study.board.framework.paging.PageMaker;
import com.study.board.framework.paging.SearchCriteria;
import com.study.board.framework.paging.SearchType;
import com.study.board.service.BoardService;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {
	private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value="/registerPage", method=RequestMethod.GET)
	public void registerGET(BoardVO board, Model model) throws Exception {
		logger.info("register GET ...............");
	}
	@RequestMapping(value="/registerPage", method=RequestMethod.POST)
	public String registerPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("register POST ...............");
		logger.info(board.toString());
		
		service.createBoard(board);
		
		rttr.addFlashAttribute("result", "success");
		
		return "redirect:/sboard/listSearch";
	}
	
	@RequestMapping(value="/listSearch", method=RequestMethod.GET)
	public void listPerPage(@ModelAttribute("pcr") SearchCriteria pcr, Model model) throws Exception {
		logger.info("show listPerPage with Criteria....");
		List<BoardVO> pageBoard = service.findBoardPerPageBySearch(pcr);
		
		model.addAttribute("listSearchPerPage", pageBoard);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setPcr(pcr);
		pageMaker.setTotalCount(service.findCountOfBoardBySearch(pcr));
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@RequestMapping(value="/readPage", method=RequestMethod.GET)
	public void readPage(@RequestParam("bno") int bno, @ModelAttribute("pcr") SearchCriteria pcr, Model model) throws Exception {
		logger.info("readPage ....");
		
		model.addAttribute(service.findBoardByID(bno));
	}
	
	@RequestMapping(value="/modifyPage", method=RequestMethod.GET)
	public void modifyPageGet(int bno, @ModelAttribute("pcr") SearchCriteria pcr, Model model) throws Exception {
		BoardVO modifyTarget = service.findBoardByID(bno);
		model.addAttribute("modifyTarget", modifyTarget);
	}
	@RequestMapping(value="/modifyPage", method=RequestMethod.POST)
	public String modifyPagePost(BoardVO obj, SearchCriteria pcr, RedirectAttributes rttr) throws Exception {
		service.updateBoard(obj);
		
		rttr.addAttribute("pageNum", pcr.getPageNum());
		rttr.addAttribute("pageSize", pcr.getPageSize());
		rttr.addAttribute("searchType", pcr.getSearchType());
		rttr.addAttribute("keyword", pcr.getKeyword());
		
		rttr.addFlashAttribute("result", "success");
		
		return "redirect:/sboard/listSearch";
	}
	
	@RequestMapping(value="/removePage", method=RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, SearchCriteria pcr, RedirectAttributes rttr) throws Exception {
		service.deleteBoard(bno);
		
		rttr.addAttribute("pageNum", pcr.getPageNum());
		rttr.addAttribute("pageSize", pcr.getPageSize());
		rttr.addAttribute("searchType", pcr.getSearchType());
		rttr.addAttribute("keyword", pcr.getKeyword());
		
		rttr.addFlashAttribute("result", "remove success");
		
		return "redirect:/sboard/listSearch";
	}
	
}
