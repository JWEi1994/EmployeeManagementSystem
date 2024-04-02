package com.ems.employeemanagement.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
public class AuditAspect {

    @Around("execution(* com.ems.employeemanagement.service.EmployeeServices.*(..))")
    public Object auditLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // Capture user's action, affected entity, and timestamp
        // Log the audit information
        return joinPoint.proceed();
    }
}
