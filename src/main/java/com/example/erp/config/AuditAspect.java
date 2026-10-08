package com.example.erp.config;

import com.example.erp.annotation.Auditable;
import com.example.erp.service.AuditLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {

    private final AuditLogService auditLogService;

    public AuditAspect(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @AfterReturning(pointcut = "@annotation(auditable)", returning = "result")
    public void audit(
            JoinPoint joinPoint,
            Auditable auditable,
            Object result) {

        Long entityId = null;

        if (result != null) {
            try {
                entityId = (Long) result.getClass()
                        .getMethod("getId")
                        .invoke(result);
            } catch (Exception ignored) {
            }
        }

        auditLogService.log(
                auditable.action(),
                auditable.entity(),
                entityId);
    }
}