package com.study.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.board.domain.ReplyVO;
import com.study.board.framework.paging.PageMaker;
import com.study.board.framework.paging.SearchCriteria;
import com.study.board.service.ReplyService;

@RestController
@RequestMapping("/reply")
public class ReplyController {
	@Inject
	private ReplyService service;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> registerReply(@RequestBody ReplyVO vo) {
		ResponseEntity<String> entity = null;

		try {
			service.addReply(vo);
			entity = new ResponseEntity<>("register Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/all/{bno}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> listReplyAll(@PathVariable("bno") Integer bno) {
		ResponseEntity<List<ReplyVO>> entity = null;
		System.out.println("dfdfdfdfd");
		try {
			entity = new ResponseEntity<>(service.listReplyAll(bno), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	@RequestMapping(value = "/{bno}/{pageNum}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listReplyPage(@PathVariable("bno") Integer bno, @PathVariable("pageNum") Integer pageNum) {
		ResponseEntity<Map<String, Object>> entity = null;
		
		try {
			SearchCriteria cri = new SearchCriteria();
			PageMaker pagemaker = new PageMaker();
			Map<String, Object> map = new HashMap<>();
			
			cri.setPageNum(pageNum);
			pagemaker.setPcr(cri);
			List<ReplyVO> replylist = service.listReplyPage(bno, cri);
			
			map.put("replylist", replylist);
			
			pagemaker.setTotalCount(service.replyCount(bno));
			
			map.put("pageMaker", pagemaker);
			
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	


	@RequestMapping(value = "/{rno}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> modifyReply(@PathVariable("rno") Integer rno, @RequestBody ReplyVO vo) {
		ResponseEntity<String> entity = null;

		try {
			vo.setRno(rno);
			service.modifyReply(vo);
			entity = new ResponseEntity<String>("modify Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
	
	@RequestMapping(value = "/{rno}", method = RequestMethod.DELETE )
	public ResponseEntity<String> removeReply(@PathVariable("rno") Integer rno) {
		ResponseEntity<String> entity = null;

		try {
			service.removeReply(rno);
			entity = new ResponseEntity<String>("remove Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
}
