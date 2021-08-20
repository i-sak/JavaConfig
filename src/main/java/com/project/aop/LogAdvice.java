package com.project.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect // Aspect를 구현한 것
@Log4j
@Component // 빈 인식
public class LogAdvice {
	@Before("execution(* com.project.service.BoardService*.*(..))")
	public void logBefore() {
		log.info("=*=*=*=*=*=*=*=");
	}
}
