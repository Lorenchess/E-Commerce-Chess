package com.chess4math.customer.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    public final static String BEFORE = "'BEFORE'";

    public final static String AFTER = "'AFTER'";

    public final static String AROUND = "'AROUND'";

    private Logger logger = LoggerFactory.getLogger(getClass());


}
