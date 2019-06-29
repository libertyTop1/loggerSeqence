package com.liberty.loggerSeqence.spring;

import org.springframework.asm.ClassVisitor;
import org.springframework.asm.Opcodes;
import org.springframework.stereotype.Component;

public class RemoveFinalFlagClassVisitor extends ClassVisitor {

    public RemoveFinalFlagClassVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM7, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        if ((access & Opcodes.ACC_FINAL) == Opcodes.ACC_FINAL){
            //remove the final flag
            access = access ^ Opcodes.ACC_FINAL;
        }
        super.visit(version, access, name, signature, superName, interfaces);
    }
}
