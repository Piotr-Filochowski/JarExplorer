package main.java;

import javassist.CtClass;

public class CustomTreeItem {

    boolean isPackage;
    String name;
    String fullName;
    CtClass ctClass;

    public boolean isPackage() {
        return isPackage;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public CtClass getCtClass() {
        return ctClass;
    }

    public CustomTreeItem(boolean isPackage, String fullName, CtClass ctClass) {
        this.isPackage = isPackage;
        this.fullName = fullName;
        this.ctClass = ctClass;
        createName(fullName);
    }

    private void createName(String fullName) {
        name = fullName.substring(fullName.lastIndexOf("."));
    }

    @Override
    public String toString() {
        return getName();
    }
}
