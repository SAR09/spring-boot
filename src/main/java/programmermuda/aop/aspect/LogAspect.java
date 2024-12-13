package programmermuda.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("target(programmermuda.aop.service.HelloService)")
    public void helloServiceMethod(){

    }

    @Before("helloServiceMethod()")
    public void beforeHelloServiceMethod(JoinPoint joinPoint){
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.info("Before " + className + " Method " + methodName);
    }


    @Around("helloServiceMethod()")
    public Object aroundHelloSercviceMethod(ProceedingJoinPoint joinPoint) throws Throwable{
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        try {
            log.info("Around Before " + className + " Method " + methodName);
            return joinPoint.proceed(joinPoint.getArgs());
        }catch (Throwable throwable){
            log.info("Around Error " + className + " Method " + methodName);
            throw throwable;
        }finally {
            log.info("Around Finally " + className + " Method " + methodName);
        }
    }


    @Pointcut("execution(* programmermuda.aop.HelloService.*(java.lang.String))")
    public void pointCutHelloServiceStringParam(){

    }

    @Before("pointCutHelloServiceStringParam()")
    public void logStringParameter(JoinPoint joinPoint){
        String value = (String) joinPoint.getArgs()[0];
        log.info("Execute method with parameter " + value);
    }

}
