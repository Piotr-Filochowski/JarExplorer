package main.java;

import javassist.CtClass;

import java.util.ArrayList;

public class Package {
    private CtClass ctClass;
    private boolean isPackage;
    private String packageName;


    public Package(String signatureName, boolean isPackage) {
        this.isPackage = isPackage;
        packageName = signatureName;
    }

    public Package(String signatureName, boolean isPackage, CtClass ctClass) {
        this(signatureName, isPackage);
        this.ctClass = ctClass;
    }

    public CtClass getCtClass() {
        return ctClass;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getFullName() {
        // TODO
        return null;
    }

    public boolean isPackage() {
        return isPackage;
    }

    @Override
    public String toString() {
        return getPackageName();
    }
}
