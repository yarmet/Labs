package com.company.lab2.logsTools;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by khamedov on 13.07.2017.
 */
@Slf4j
@Aspect
@Component
public class LogTool {

    @Before("@annotation(com.company.lab2.logsTools.Loggable)")
    public void before(JoinPoint point) {
        StringBuilder builder = new StringBuilder("CALLED METHOD : '")
                .append(point.getSignature().getName())
                .append("'  WITH ARGUMENTS : ");

        Object[] arguments = point.getArgs();

        for (int i = 0; i < arguments.length; i++) {
            builder.append(arguments[i]);
        }
        log.info(builder.toString());
    }
}