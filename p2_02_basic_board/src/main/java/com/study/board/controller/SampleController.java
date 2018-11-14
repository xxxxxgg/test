package com.study.board.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.board.domain.BoardVO;

@RestController
@RequestMapping("/reply1")
public class SampleController {
	@RequestMapping("/hello")
	public String sayHello() {
		return "hello worldzzzzz";
	}
	
	@RequestMapping("/sendVO")
	public BoardVO sendVO() {
		BoardVO vo = new BoardVO();
		vo.setBno(2);
		vo.setContent("내용이다");
		vo.setRegdate(new Date());
		vo.setTitle("제목이다");
		vo.setViewcnt(4);
		vo.setWriter("홍길동");
		
		return vo;
	}

	@RequestMapping("/sendList")
	public List<BoardVO> sendList() {
		List<BoardVO> listvo = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			BoardVO vo = new BoardVO();
			vo.setBno(i);
			vo.setContent("내용이다" + i);
			vo.setRegdate(new Date());
			vo.setTitle("제목이다" + i);
			vo.setViewcnt(i);
			vo.setWriter("홍길동" + i);

			listvo.add(vo);
		}

		return listvo;
	}

	@RequestMapping("/sendMap")
	public Map<String, BoardVO> sendMap() {
		Map<String, BoardVO> mapvo = new HashMap<>();
		
		for (int i = 0; i < 10; i++) {
			BoardVO vo = new BoardVO();
			vo.setBno(i);
			vo.setContent("내용이다" + i);
			vo.setRegdate(new Date());
			vo.setTitle("제목이다" + i);
			vo.setViewcnt(i);
			vo.setWriter("홍길동" + i);

			mapvo.put("map" + i, vo);
		}

		return mapvo;
	}
}
