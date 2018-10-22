package com.vil.pjt.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SampleAdvice {
	private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);
	
	@Before("execution(* com.vil.pjt.service.SampleService*.*(..))")
	public void startLog() {
		logger.info("------------------------------------zz");
		logger.info("------------------------------------zz");
	}
}
