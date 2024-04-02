package com.ems.employeemanagement.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class AuditAspect {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AuditAspect.class);

    @Before("execution(* com.ems.employeemanagement.service.EmployeeServices.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        LOGGER.info("Method " + joinPoint.getSignature().getName() + " is called");
    }
}
