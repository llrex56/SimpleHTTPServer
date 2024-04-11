package springboot.demo.interceptor;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class InterfaceAuthCheckInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object obj) {
        if (logger.isInfoEnabled()) {
            logger.info("interceptor url：" + request.getRequestURL());
        }
        System.out.println(request.getHeader("Content-Type"));
        return true;
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest arg0, @NotNull HttpServletResponse arg1, @NotNull Object arg2, Exception arg3) {
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest arg0, @NotNull HttpServletResponse arg1, @NotNull Object arg2, ModelAndView arg3) {
    }
}