package com.vil.pjt.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vil.pjt.HomeController;
import com.vil.pjt.domain.JsonVO;

@Controller
@RequestMapping("/")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Locale locale) {
		logger.info("Welcome home! The client locale is {}.-----------------------------", locale);
		
		return "home";
	}
	
	@RequestMapping(value = "/json", method = RequestMethod.GET)
	public @ResponseBody JsonVO json() {
		JsonVO vo = new JsonVO();
		return vo;
	}
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public void ajaxtest() {
		
	}
}
