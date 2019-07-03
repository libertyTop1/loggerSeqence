/** Copyright (C), 和信电子商务有限公司 */
package hexindai;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @FileName LogAspect
 *
 * @describe 串行化日志
 * @author shijudong
 * @create 2019/6/28 0028 上午 10:47
 */
@Aspect
@Component
public class LogAspect {

  private final ThreadLocal<Long> serialNumber = new ThreadLocal<>();
  private final Map<Class, Logger> loggerMap = new ConcurrentHashMap<>();
  private final AtomicInteger random = new AtomicInteger(0);

//  @Pointcut("within(com.hexin.lego.common.logger.LegoLog)")
  public void bizLog() {}

  @Pointcut(
      "@within(org.springframework.stereotype.Controller) "
          + " || @within(org.springframework.web.bind.annotation.RestController)")
//          + " || @within(com.xxl.job.core.handler.annotation.JobHandler)")
  public void attachedSource() {}

  @Around("attachedSource()")
  public Object appendSerialNumber(ProceedingJoinPoint joinPoint) throws Throwable {
    if (random.get() >= 1000000) random.set(0);
    serialNumber.set(System.currentTimeMillis() + random.addAndGet(5));
    try {
      return joinPoint.proceed(joinPoint.getArgs());
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      throw throwable;
    } finally {
      serialNumber.remove();
    }
  }

  @Around("bizLog()")
  public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
    Class clazz = ((LegoLog) joinPoint.getTarget()).getAttachedClass();
    Object[] args = joinPoint.getArgs();
    if (clazz == null || args[0] instanceof Marker) {
      return joinPoint.proceed(args);
    }
    if (!loggerMap.containsKey(clazz)) {
      loggerMap.put(clazz, LoggerFactory.getLogger(clazz));
    }
    int length = args.length;
    String firstArg = "{}" + "[{}] " + args[0];
    Object[] secondArg;
    if (length == 1) {
      secondArg = new Object[2];
      secondArg[0] = "流程统一标识：";
      secondArg[1] = serialNumber.get();
    } else {
      Object[] initial;
      if (args[1] instanceof Object[]) {
        initial = (Object[]) args[1];
      } else {
        initial = new Object[args.length - 1];
        System.arraycopy(args, 1, initial, 0, initial.length);
      }
      secondArg = new Object[initial.length + 2];
      secondArg[0] = "流程统一标识：";
      secondArg[1] = serialNumber.get();
      System.arraycopy(initial, 0, secondArg, 2, initial.length);
    }

    Logger logger = loggerMap.get(clazz);
    // 统一利用一个可传多参的方法来处理
    String signature = joinPoint.getSignature().getName();
    Method print =
        logger.getClass().getMethod(signature, new Class[] {String.class, Object[].class});
    return print.invoke(logger, firstArg, secondArg);
  }
}
