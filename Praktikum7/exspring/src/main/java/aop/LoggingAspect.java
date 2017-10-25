package aop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static Logger LOG = LogManager.getLogger(LoggingAspect.class);

    @Before("execution(* service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) throws Throwable {

        LOG.debug("method name: " + joinPoint.getSignature().getName());
        LOG.debug("method arguments : " + Arrays.toString(joinPoint.getArgs()));
    }
}