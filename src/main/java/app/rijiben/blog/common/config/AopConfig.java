package app.rijiben.blog.common.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy
public class AopConfig {
    final Logger log = LoggerFactory.getLogger(AopConfig.class);

    @Around("execution(* app.rijiben.blog.service..*.*(..))") // 定义切点表达式
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        // 在方法执行前可以做的事情
        log.info("Before method:{}", joinPoint.getSignature());
        Object result = joinPoint.proceed(); // 继续执行原方法
        // 在方法执行后可以做的事情
        log.info("After method:{}", joinPoint.getSignature());
        return result;
    }
    
    @AfterThrowing(pointcut = "execution(* app.rijiben.blog.controller..*.*(..))", throwing = "ex")
    public void afterThrowing(Exception ex) {
        log.info("异常信息:", ex);
    }
}
