package main.java;

import javassist.CtClass;

import java.util.ArrayList;

public class Package {
    private ArrayList<CtClass> classContent;
    private ArrayList<Package> packagesContent;
    private Package parent;
    private String packageName;
    private String fullName;

    public Package(Package parent, String fullName) {
        this.parent = parent;
        this.fullName = fullName;
        classContent = new ArrayList<CtClass>();
        packagesContent = new ArrayList<Package>();
    }

    public ArrayList<CtClass> getClassContent() {
        return classContent;
    }

    public ArrayList<Package> getPackagesContent() {
        return packagesContent;
    }

    public Package getParent() {
        return parent;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setClassContent(ArrayList<CtClass> classContent) {
        this.classContent = classContent;
    }

    public void setPackagesContent(ArrayList<Package> packagesContent) {
        this.packagesContent = packagesContent;
    }

    public void setParent(Package parent) {
        this.parent = parent;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
