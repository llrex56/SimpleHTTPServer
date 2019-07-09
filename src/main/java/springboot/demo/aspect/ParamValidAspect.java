package springboot.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Component
@Order(2)
public class ParamValidAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * springboot.demo.controller..*.*(..))")
    public void validateParams() throws Throwable {
    }

    @Before("validateParams()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
    }

    @AfterReturning(returning = "ret", pointcut = "validateParams()")
    public void doAfterReturning(Object ret) throws Throwable {
    }

    @Around("validateParams()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        // 获得切入方法参数
        Object [] args = pjp.getArgs();

        BindingResult bindingResult = this.getBindingResult(args);

        if (bindingResult != null && bindingResult.hasErrors()) {
            if (logger.isInfoEnabled()) {
                // 获得切入的方法
                Method method = ((MethodSignature) pjp.getSignature()).getMethod();
                logger.info("Params valid failure, method：{}.", method.getName());
            }
            return this.getErrorsSplitNewLine(bindingResult);
        }
        return pjp.proceed();
    }

    private BindingResult getBindingResult(Object[] args) {
        for (Object arg: args) {
            if (arg != null && arg instanceof BindingResult) {
                return (BindingResult) arg;
            }
        }
        return null;
    }

    /*
     * 此校验错误信息转化为字符，多个错误信息通过参数[splitChars]进行分隔
     */
    private String getErrors(BindingResult br, String splitChars) {
        if(splitChars == null) {
            splitChars = "";
        }
        StringBuilder result = new StringBuilder();
        List<ObjectError> errors = br.getAllErrors();
        for (ObjectError vError : errors) {
            result.append(vError.getDefaultMessage());
            result.append(splitChars);
        }
        if(result.length() > 0) {
            result.delete(result.length() - splitChars.length(), result.length());
        }
        return result.toString();
    }

    /*
     * 此校验错误信息转化为字符，多个错误信息通过<br>进行分隔
     */
    private String getErrorsSplitNewLine(BindingResult br) {
        return getErrors(br, "<br>");
    }
}
