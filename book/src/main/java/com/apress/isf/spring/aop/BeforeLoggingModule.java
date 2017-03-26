package com.apress.isf.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BeforeLoggingModule {
    private static final Logger log = LoggerFactory.getLogger(BeforeLoggingModule.class);

    @Before("execution(* com.apress.isf.spring.service.SearchEngineService.*(..))")
    public void before(JoinPoint joinPoint) {
        log.info("@@@@(BEFORE) Method called: " + joinPoint.getSignature().getName());
        if(joinPoint.getArgs().length == 0)
            log.info("@@@@(BEFORE) No arguments passed.");
        for(Object arg:joinPoint.getArgs())
            log.info("@@@@(BEFORE) Argument passed: " + arg);
    }

    @AfterReturning(pointcut = "execution(* com.apress.isf.spring.service.SearchEngineService.*(..))",
                    returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        log.info("@@@@(AFTER) Method called: " + joinPoint.getSignature().getName());
        if(joinPoint.getArgs().length ==0 )
            log.info("@@@@(AFTER) No arguments passed.");
        for(Object arg:joinPoint.getArgs())
            log.info("@@@@(AFTER) Argument passed: " + arg);
        log.info("@@@(AFTER) Result: " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.apress.isf.spring.service.SearchEngineService.*(..))",
                    throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception) {
        log.info("@@@@(AFTER THROWING) Method called: " + joinPoint.getSignature().getName());
        log.info("@@@@(AFTER THROWING) Exception: " + exception.toString());
    }
}
