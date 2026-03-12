package wordbook.redis;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RedisAop {
    @Around("@annotation(wordbook.redis.TimeTrace)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint);

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            System.out.println("END: " + joinPoint + " " + (finish - start) + "ms");
        }
    }
}
