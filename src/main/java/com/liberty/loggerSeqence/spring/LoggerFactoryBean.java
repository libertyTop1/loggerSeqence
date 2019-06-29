package com.liberty.loggerSeqence.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

public class LoggerFactoryBean implements FactoryBean<Logger> {

    static {
        try {
            MockFinalLogger classLoader = new MockFinalLogger();
            Class finalObjectClass = Class.forName("org.slf4j.Logger", true, classLoader);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

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
