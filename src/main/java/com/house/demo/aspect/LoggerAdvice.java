package com.house.demo.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author xjj
 */
//@Aspect
//@Component
public class LoggerAdvice {

    private Logger logger = LoggerFactory.getLogger(LoggerAdvice.class);

    @Pointcut(value = "execution(* com.house.demo.controller.*.*(..))")
    public void myPointcut(){
    }

    /**
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("myPointcut()")
    public Object myLogger(ProceedingJoinPoint pjp) throws Throwable {

        String className = pjp.getTarget().getClass().toString();

        String methodName = pjp.getSignature().getName();

        Object[] array = pjp.getArgs();


        ObjectMapper objectMapper = new ObjectMapper();

        logger.info("调用前： "+className+":"+methodName+"传递的参数为："+objectMapper.writeValueAsString(array));

        Object object =pjp.proceed();

        logger.info("调用后： "+className+":"+methodName+"返回值为："+objectMapper.writeValueAsString(object));
        return object;

    }



}
