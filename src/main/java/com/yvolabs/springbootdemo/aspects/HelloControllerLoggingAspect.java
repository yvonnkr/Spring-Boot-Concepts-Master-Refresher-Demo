package com.yvolabs.springbootdemo.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class HelloControllerLoggingAspect {

    @Before(value = "execution(* com.yvolabs.springbootdemo.controller.HelloController.*(..))")
    public void logHelloAdvice(JoinPoint jp) {
        log.info("HELLO @Before: {}() method", jp.getSignature().getName());
    }
}
