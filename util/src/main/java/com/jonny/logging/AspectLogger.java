package com.jonny.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
class AspectLogger {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AspectLogger.class);

    @Before(" within(com.jonny.service..*)")
    public void addBefore(JoinPoint joinPoint){
        Object[] params = joinPoint.getArgs();
        if(params.length==1)
            LOGGER.debug("Run method '"+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName()+"( arg: "+String.valueOf(params[0])+" )'.");
        else
            LOGGER.debug("Run method '"+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName()+"'");
    }

    @AfterThrowing(pointcut = " within(com.jonny..*)", throwing = "error")
    public void addAfterThrowing(JoinPoint joinPoint, RuntimeException error){
        LOGGER.warn("Throwing '"+error.getClass().getName()+"' into method '"+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName()+"'");
        LOGGER.warn("Exception message: "+error.getMessage());
    }
}
