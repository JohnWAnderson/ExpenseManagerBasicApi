package com.jwa.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private Logger myLog = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.jwa.controller.*.*(..))")
	private void controllerPackage() {}
	
	@Pointcut("execution(* com.jwa.errors.*.*(..))")
	private void errorPackage() {}
	
	@Pointcut("execution(* com.jwa.repository.*.*(..))")
	private void repositoryPackage() {}
	
	@Pointcut("controllerPackage() || errorPackage() || repositoryPackage()")
	private void AppFlow() {}
	
	@Before("AppFlow()")
	public void beforeFlow(JoinPoint point) {
		String method = point.getSignature().toShortString();
		myLog.info("@Before method => "+ method);
		Object[] args = point.getArgs();
		for(Object temp : args)
			myLog.info("Arguments ==> " + temp);
		}
	
	@AfterReturning(pointcut = "AppFlow()", returning = "result")
	public void afterFlow(JoinPoint point, Object result){
		String method = point.getSignature().toShortString();
		myLog.info("@AfterReturning method => "+ method);
		myLog.info("Result ==> " + result);
	}
}
