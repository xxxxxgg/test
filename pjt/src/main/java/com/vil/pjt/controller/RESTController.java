package com.vil.pjt.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vil.pjt.domain.SampleVO;
import com.vil.pjt.service.SampleService;

@RestController
@RequestMapping("/messages")
public class RESTController {
	@Inject
	private SampleService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<SampleVO>> selectSample(@RequestBody SampleVO vo) {
		ResponseEntity<List<SampleVO>> entity = null;

		try {
			entity = new ResponseEntity<>(service.listAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

	@RequestMapping(value = "/reply/all", method = RequestMethod.GET)
	public ResponseEntity<List<SampleVO>> replyall() {
		ResponseEntity<List<SampleVO>> entity = null;

		try {
			entity = new ResponseEntity<>(service.listAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
	
	@RequestMapping(value = "/tran", method = RequestMethod.GET)
	public ResponseEntity<String> tran() {
		ResponseEntity<String> entity = null;
		SampleVO vo = new SampleVO();
		
		vo.setMid("tranuser1");
		vo.setMpw("tranuser1");
		vo.setMname("tranuser1");
		vo.setMemail("tranuser1");
		
		try {
			service.addTransaction(vo);
			entity = new ResponseEntity<>("tran success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

	/*
	 * @RequestMapping(value = "/reply/all", method = RequestMethod.GET) public
	 * List<SampleVO> replyall() { List<SampleVO> list = new ArrayList<>(); for
	 * (int i = 0; i < 10; i++) { SampleVO vo = new SampleVO(); vo.setId("id" +
	 * i); vo.setPw("pw" + i); vo.setName("name" + i); vo.setEmail("email" + i);
	 * 
	 * list.add(vo); }
	 * 
	 * return list; }
	 */
}
