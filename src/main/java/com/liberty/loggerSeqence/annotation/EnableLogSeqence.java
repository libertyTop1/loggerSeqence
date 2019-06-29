package com.liberty.loggerSeqence.annotation;

import com.liberty.loggerSeqence.spring.LibertyLoggerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LibertyLoggerRegistrar.class)
public @interface EnableLogSeqence {
}
