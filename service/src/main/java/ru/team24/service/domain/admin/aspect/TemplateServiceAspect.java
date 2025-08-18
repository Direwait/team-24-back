package ru.team24.service.domain.admin.aspect;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TemplateServiceAspect {

    @Around("execution(* ru.team24.service.impl.domain.admin.TemplateServiceImpl.*(..))")
    public Object logAndTimeMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("Вызов метода: {} с аргументами {}", methodName, args);

        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long end = System.currentTimeMillis();

            log.info("Метод {} успешно завершен. Результат: {}. Время выполнения: {} мс",
                    methodName, result, (end - start));
            return result;

        } catch (EntityNotFoundException enf) {
            log.warn("EntityNotFoundException в методе {}: {}", methodName, enf.getMessage());
            throw enf;

        } catch (Exception ex) {
            log.error("Ошибка в методе {}: {}", methodName, ex.getMessage(), ex);
            throw ex;
        }
    }
}
