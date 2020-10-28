package javaserver.aop;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/*
 * 
  使用@Aspect将一個java clas 定義為切面
  使用@Pointcut定義一個切入點，可以是一个规则表达式，例如某個package下的所有method，也可以是一個註解等。
  根據需要在切入點不同位置的切入内容
  使用@Before在切入點開始處切入内容
  使用@After在切入点结尾處切入内容
  使用@AfterReturning在切入點return内容之後切入内容（可以用来對處理返回值做一些加工處理）
  使用@Around在切入點前後切入内容，并自己控制何時執行切入點自身的内容
  使用@AfterThrowing用来處理當切入内容部分拋出exception之後的處理邏輯
 *
 */


@Aspect
@Component
public class ServiceLog {

    private final static Logger LOGGER = Logger.getLogger(ServiceLog.class);

    @Pointcut("execution( * javaserver.*.*(..))")
    public void service() {
    }

    @Before("service()")
    public void beforeExecute(JoinPoint joinPoint) {
        String classname = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        LOGGER.info("before Execute! --class name: " + classname + ", method name: " + methodName + " " + args);
    }

    @After("service()")
    public void callServiceLog() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LOGGER.info("URL : " + request.getRequestURL().toString());
    }

}
