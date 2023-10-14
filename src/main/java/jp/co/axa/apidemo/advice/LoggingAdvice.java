package jp.co.axa.apidemo.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * LoggingAdvice is an Aspect class responsible for logging method invocations
 * and their responses in controllers within the "jp.co.axa.apidemo.controllers" package.
 * It uses Aspect-Oriented Programming (AOP) to intercept method calls and log
 * relevant information.
 */
@Aspect
@Component
public class LoggingAdvice {

	private Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

    // Define a pointcut for methods in the controllers package
    @Pointcut(value = "execution(* jp.co.axa.apidemo.controllers.*.*(..))")
    public void targetPointcut() {
    }

    // Log method invocations and responses
    @Around("targetPointcut()")
    public Object logMethodInvocation(ProceedingJoinPoint joinPoint) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();

        // Log method invocation
        log.info("Method invoked: " + className + "." + methodName + "()" + " with argument : "
                + mapper.writeValueAsString(args));

        Object object = joinPoint.proceed(); // Proceed with the method execution

        // Log method response
        log.info(className + "." + methodName + "()" + " Response : "
                + mapper.writeValueAsString(object));

        return object;
    }
}
