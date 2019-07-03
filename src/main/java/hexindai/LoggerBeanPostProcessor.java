/** Copyright (C), 和信电子商务有限公司 */
package hexindai;

//import com.hexin.lego.job.service.CommonService;
//import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @FileName LoggerBeanPostProcessor
 *
 * @describe
 * @author shijudong
 * @create 2019/7/2 0002 上午 11:04
 */
@Component
public class LoggerBeanPostProcessor
    implements BeanPostProcessor, InstantiationAwareBeanPostProcessor, BeanFactoryAware {

  private BeanFactory beanFactory;

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

    boolean needWire = false;

    Set<Annotation> annotations = new HashSet<>();

    annotations.addAll(Arrays.asList(bean.getClass().getAnnotations()));
    annotations.addAll(Arrays.asList(bean.getClass().getSuperclass().getAnnotations()));

    for (Annotation annotation : annotations) {
      Class annotationType = annotation.annotationType();
      if (annotationType.isAssignableFrom(RestController.class)
          || annotationType.isAssignableFrom(Controller.class)
          || annotationType.isAssignableFrom(Service.class)
          || annotationType.isAssignableFrom(Repository.class)
//          || annotationType.isAssignableFrom(JobHandler.class)) {
      ){
      needWire = true;
        break;
      }
    }

    if (!needWire) return bean;

    try {
      Object target = AopTargetUtils.getTarget(bean);
      Field logger = target.getClass().getDeclaredField("logger");
      logger.setAccessible(true);
      LegoLog legoLog = beanFactory.getBean(LegoLog.class);
      legoLog.setAttachedClass(target.getClass());
      logger.set(target, legoLog);

      //if (target instanceof CommonService) {
      if (true){
        Field loggerInCommonService = target.getClass().getSuperclass().getDeclaredField("logger");
        loggerInCommonService.setAccessible(true);
        loggerInCommonService.set(target, legoLog);
      }

    } catch (Exception e) {
      // e.printStackTrace();
    }
    return bean;
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    this.beanFactory = beanFactory;
  }
}
