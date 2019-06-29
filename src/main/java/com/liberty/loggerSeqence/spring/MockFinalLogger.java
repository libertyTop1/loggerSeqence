package com.liberty.loggerSeqence.spring;

import org.springframework.asm.ClassReader;
import org.springframework.asm.ClassWriter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MockFinalLogger extends ClassLoader {

    public MockFinalLogger(){
        super(null);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            ClassReader reader = new ClassReader(name);
            ClassWriter writer = new ClassWriter(reader, 0);
            RemoveFinalFlagClassVisitor classVisitor = new RemoveFinalFlagClassVisitor(writer);
            reader.accept(classVisitor, ClassReader.SKIP_CODE);
            byte[] bytes = writer.toByteArray();
            return defineClass(name, bytes,0,bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }
}
