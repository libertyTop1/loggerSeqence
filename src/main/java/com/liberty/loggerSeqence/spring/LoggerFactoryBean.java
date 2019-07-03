package com.liberty.loggerSeqence.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

public class LoggerFactoryBean implements FactoryBean<Logger> {

    private Class attachedSource;

    public LoggerFactoryBean(Class attachedSource){
        this.attachedSource = attachedSource;
    }

    @Override
    public Logger getObject() throws Exception {
        return LoggerFactory.getLogger(attachedSource);
    }

    @Override
    public Class<?> getObjectType() {
        return Logger.class;
    }
}
