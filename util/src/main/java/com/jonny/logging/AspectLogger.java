package com.jonny.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
class AspectLogger {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AspectLogger.class);

    @AfterThrowing(pointcut = " execution(* com.jonny.service.*.*(..))", throwing = "error")
    public void addAfterThrowingService(JoinPoint joinPoint, RuntimeException error) {
        LOGGER.error("Throwing '" + error.getClass().getName() + "' into method '" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "'");
        LOGGER.error("Exception message:" + error.getLocalizedMessage());
    }


}
