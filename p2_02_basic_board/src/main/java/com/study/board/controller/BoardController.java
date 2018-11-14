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
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void registerGET(BoardVO board, Model model) throws Exception {
		logger.info("register GET ...............");
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("register POST ...............");
		logger.info(board.toString());
		
		service.createBoard(board);
		
		rttr.addFlashAttribute("result", "success");
		
		return "redirect:/board/listPerPage";
	}

	/*@RequestMapping(value="/listAll", method=RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		logger.info("show all list ...............");
		List<BoardVO> allBoard = service.findAllBoard();
		
		model.addAttribute("allBoard", allBoard);
	}*/
	
	@RequestMapping(value="/listPerPage", method=RequestMethod.GET)
	public void listPerPage(@ModelAttribute("pcr") SearchCriteria pcr, Model model) throws Exception {
		logger.info("show listPerPage with Criteria....");
		List<BoardVO> pageBoard = service.findListOfBoardPerPage(pcr);
		
		model.addAttribute("listBoardPerPage", pageBoard);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setPcr(pcr);
		pageMaker.setTotalCount(service.getBoardCount(pcr));
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, @ModelAttribute("pcr") PageCriteria pcr, Model model) throws Exception {
		BoardVO board = service.findBoardByID(bno);
		model.addAttribute(board);
	}
	
	/*@RequestMapping(value="/remove", method=RequestMethod.GET)
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {
		service.deleteBoard(bno);
		rttr.addFlashAttribute("result", "success");
		return "redirect:/board/listAll";
	}*/
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, PageCriteria pcr, RedirectAttributes rttr) throws Exception {
		service.deleteBoard(bno);
		
		rttr.addAttribute("pageNum", pcr.getPageNum());
		rttr.addAttribute("pageSize", pcr.getPageSize());
		rttr.addFlashAttribute("result", "remove success");
		
		return "redirect:/board/listPerPage";
	}
	@RequestMapping(value="/batchDelete", method=RequestMethod.GET)
	public String batchDelete(@RequestParam("batchTarget") Integer[] arrbno, PageCriteria pcr, RedirectAttributes rttr) throws Exception {
		logger.info("batchDelete GET");
		
		service.batchDeleteBoard(arrbno);
		
		rttr.addAttribute("pageNum", pcr.getPageNum());
		rttr.addAttribute("pageSize", pcr.getPageSize());
		rttr.addFlashAttribute("result", "remove success");
		
		return "redirect:/board/listPerPage";
	}

	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public void modifyGet(@RequestParam("bno") int bno, @ModelAttribute("pcr") PageCriteria pcr, Model model) throws Exception {
		BoardVO modifyTarget = service.findBoardByID(bno);
		model.addAttribute("modifyTarget", modifyTarget);
	}

	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyPost(BoardVO obj, PageCriteria pcr, RedirectAttributes rttr) throws Exception {
		service.updateBoard(obj);
		
		rttr.addAttribute("pageNum", pcr.getPageNum());
		rttr.addAttribute("pageSize", pcr.getPageSize());
		rttr.addFlashAttribute("result", "success");
		
		return "redirect:/board/listPerPage";
	}
	
}
