package com.chess4math.customer.aspects;

import com.chess4math.customer.exceptions.DuplicatedEmailException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DBExceptionHandlingAspect {
    public static final String EMAIL_DUPLICATION_MESSAGE = "Email address is already registered. ";

    private Logger logger = LoggerFactory.getLogger(getClass());

    @AfterThrowing(value = "execution(public * com.chess4math.customer.services.*.*(..))", throwing = "exception")
    public void implDBExceptionHandling(DuplicatedEmailException exception) {
        logger.warn(EMAIL_DUPLICATION_MESSAGE + exception + "\n");
    }
}
