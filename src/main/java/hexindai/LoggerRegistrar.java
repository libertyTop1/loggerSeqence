/**
 * Copyright (C), 和信电子商务有限公司
 */
package hexindai;

import com.liberty.loggerSeqence.spring.LoggerFactoryBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.beans.factory.config.AutowireCapableBeanFactory.AUTOWIRE_NO;

/**
 * @FileName LoggerRegistrar
 * @describe
 * @author shijudong
 * @create 2019/6/28 0028 下午 3:10
 */
public class LoggerRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        List<String> beanNameList = Arrays.asList(registry.getBeanDefinitionNames()).stream().filter(beanName -> beanName.endsWith("Job")).collect(Collectors.toList());
        beanNameList.forEach(beanName -> {

            try {
                RootBeanDefinition beanDefinition = new RootBeanDefinition();
                beanDefinition.setBeanClass(LoggerFactoryBean.class);
                beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
                beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(registry.getBeanDefinition(beanName).getBeanClassName());
                registry.registerBeanDefinition(beanName + "Logger", beanDefinition);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }
}
