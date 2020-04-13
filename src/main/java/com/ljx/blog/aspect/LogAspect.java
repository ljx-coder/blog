package com.ljx.blog.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 * @author: Mr.Liu
 * @description: AOP切面处理日志记录
 * @date: 2020-03-31 21:23
 */
@Aspect
@Slf4j
@Component
public class LogAspect {

    /**
    * @Description: 定义通知切入点 
    * @Param: [] 
    * @return: void 
    * @Date: 2020/3/31-22:37
    */
    @Pointcut("execution(public * com.ljx.blog.controller..*.*(..))")
    public void webLog(){}

    /**
    * @Description: 定义前置通知
     * 通过JoinPoint可以获得通知的签名信息，如目标方法名、目标方法参数信息等；
     * 通过RequestContextHolder来获取请求信息，Session信息；
    * @Param: [joinPoint] 
    * @return: void 
    * @Date: 2020/3/31-22:38
    */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {

        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =attributes.getRequest();
        //记录以下内容
        log.info("URL:"+request.getRequestURL().toString());
        log.info("IP:"+request.getRemoteAddr());
        log.info("CLASS_METHOD:"+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        log.info("ARGS:"+ Arrays.toString(joinPoint.getArgs()));
    }

    @After("webLog()")
    public void doAfter() {
        //log.info("-----------after-------------");
    }

    /**
    * @Description: 定义后置返回通知
     * 如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     * 如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning：限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，
     * 对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
    * @Param: [result] 
    * @return: void 
    * @Date: 2020/3/31-22:38
    */
    @AfterReturning(returning = "result",pointcut = "webLog()")
    public void afterReturn(Object result) {
        log.info("result:"+result);
    }

}
