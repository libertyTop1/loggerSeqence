package com.liberty.loggerSeqence;

import com.liberty.loggerSeqence.annotation.EnableLogSeqence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;

@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableLogSeqence
public class LoggerSeqenceApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LoggerSeqenceApplication.class, args);
		Arrays.asList(context.getBeanDefinitionNames()).stream().forEach(beanName -> System.out.println(beanName));
	}

}
