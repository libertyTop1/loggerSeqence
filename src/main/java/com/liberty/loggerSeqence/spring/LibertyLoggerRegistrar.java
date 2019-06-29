package com.liberty.loggerSeqence.spring;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LibertyLoggerRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        List<String> beanNames =  Arrays.asList(beanDefinitionRegistry.getBeanDefinitionNames()).stream().filter((beanName) -> beanName.endsWith("Controller")).collect(Collectors.toList());
        for (String beanName : beanNames) {
            GenericBeanDefinition beanDefinition = (GenericBeanDefinition) BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
            beanDefinition.setBeanClass(LoggerFactoryBean.class);
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanDefinitionRegistry.getBeanDefinition(beanName).getBeanClassName());
            beanDefinitionRegistry.registerBeanDefinition(beanName+"Logger", beanDefinition);
        }
    }
}
