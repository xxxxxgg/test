package com.study.board.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SampleAdvice {
	private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);

	@Before("execution(* com.study.board.service.*.*(..))")
	public void startLog(JoinPoint jp) {
		logger.info("---------------------------------------zz");
		logger.info("---------------------------------------zz");
		logger.info(Arrays.toString(jp.getArgs()));
		System.out.println("--------------------------------zz");
	}
	@After("execution(* com.study.board.service.*.*(..))")
	public void endLog(JoinPoint jp) {
		logger.info("---------------------------------------zz");
		logger.info("---------------------------------------zz");
		logger.info(Arrays.toString(jp.getArgs()));
		System.out.println("--------------------------------zz");
	}
	@Around("execution(* com.study.board.service.MessageService*.*(..))")
	public void Log(JoinPoint jp) {
		logger.info("---------------------------------------zz");
		logger.info("---------------------------------------zz");
		logger.info(Arrays.toString(jp.getArgs()));
		System.out.println("--------------------------------zz");
	}
	@Around("execution(* com.study.board.service.MessageServiceImpl.*(..))")
	public void Log1(JoinPoint jp) {
		logger.info("---------------------------------------zz");
		logger.info("---------------------------------------zz");
		logger.info(Arrays.toString(jp.getArgs()));
		System.out.println("--------------------------------zz");
	}
}
