package com.liberty.loggerSeqence.aspect;

import com.liberty.loggerSeqence.util.LogUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Aspect
@Component
public class LogAspect {

    ThreadLocal<Long> serialNumber = new ThreadLocal<>();
    Map<Class, Logger> loggerMap = new ConcurrentHashMap<>();
    private AtomicInteger random = new AtomicInteger(0);

    // 无效果，因为withIn具体的类才可以，而且具体的类不能是final的，因为要创建代理
    // TODO : 如何对第三方jar包进行AOP，需要再究
   /* @Pointcut("within(org.slf4j.Logger)")
    public void bizLog(){}*/

    @Pointcut("within(com.liberty.loggerSeqence.util.LogUtil)")
    public void bizLog(){}

    @Pointcut("within(com.liberty.loggerSeqence.contrller.*)")
    public void attachedSource(){}

    @Around("attachedSource()")
    public Object appendSerialNumber(ProceedingJoinPoint joinPoint) throws Throwable {
        if (random.get() >= 1000000) random.set(0);
        serialNumber.set(System.currentTimeMillis() + random.addAndGet(5));
        try {
            Object result = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw throwable;
        }finally {
            serialNumber.set(null);
        }
        return null;
    }

    @Around("bizLog()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        Class clazz = ((LogUtil)joinPoint.getTarget()).getAttachedClass();
        Object[] args = joinPoint.getArgs();
        if (clazz == null || args[0] instanceof Marker){
            joinPoint.proceed(args);
            return null;
        }
        if( !loggerMap.containsKey(clazz)){
            loggerMap.put(clazz, LoggerFactory.getLogger(clazz));
        }
        int length = args.length;
        String firstArg = "{}"+"[{}] "+args[0];
        Object[] secondArg;
        if (length == 1) {
            secondArg = new Object[2];
            secondArg[0] = "流程统一标识：";
            secondArg[1] = serialNumber.get();
        }else {
            Object[] initial = (Object[]) args[1];
            secondArg = new Object[initial.length + 2];
            secondArg[0] =  "流程统一标识：";
            secondArg[1] =  serialNumber.get();
            System.arraycopy(initial,0, secondArg,2,initial.length);
        }

        Logger logger = loggerMap.get(clazz);
        // 统一利用一个可传多参的方法来处理
        Method info = logger.getClass().getMethod("info", new Class[]{String.class, Object[].class});
        info.invoke(logger, firstArg, secondArg);

        return null;
    }
}
