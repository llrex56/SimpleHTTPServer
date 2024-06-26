package springboot.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

//@Aspect
//@Component
//@Order(1)
public class WebLogAspect {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<>();


    @Pointcut("execution(public * springboot.demo.controller..*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        if (logger.isInfoEnabled()) {
            startTime.set(System.currentTimeMillis());

            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();

                // 记录下请求内容
                logger.info("URL : {}.", request.getRequestURL().toString());
                logger.info("HTTP_METHOD : {}.", request.getMethod());
                logger.info("IP : {}." + request.getRemoteAddr());
                logger.info("CLASS_METHOD : {}.", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
                logger.info("ARGS : {}.", Arrays.toString(joinPoint.getArgs()));
            }
        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        if (logger.isInfoEnabled()) {
            logger.info("RESPONSE: {}.", ret);
            logger.info("SPEND TIME: {}.", (System.currentTimeMillis() - startTime.get()));
        }
    }
}
