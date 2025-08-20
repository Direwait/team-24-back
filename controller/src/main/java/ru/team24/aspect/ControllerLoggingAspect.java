package ru.team24.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class ControllerLoggingAspect {

    @Pointcut("within(ru.team24.controller..*)")
    public void allControllers() {}

    @Around("allControllers()")
    public Object logControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        log.debug("[Controller] method {}.{}() started", className, methodName);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try {
            Object result = joinPoint.proceed();
            stopWatch.stop();

            if (result instanceof ResponseEntity response) {
                log.info("[Controller] {}.{}() - Status: {}, Time: {}ms",
                        className, methodName, response.getStatusCode(), stopWatch.getTotalTimeMillis());
            } else {
                log.info("[Controller] {}.{}() - Time: {}ms",
                        className, methodName, stopWatch.getTotalTimeMillis());
            }

            return result;

        } catch (Exception e) {
            stopWatch.stop();
            log.error("[Controller] {}.{}() FAILED - Time: {}ms, Error: {}",
                    className, methodName, stopWatch.getTotalTimeMillis(), e.getMessage());
            throw e;
        }
    }
}