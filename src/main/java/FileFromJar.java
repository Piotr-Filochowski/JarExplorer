package main.java;

public class FileFromJar {

    private String fullPath;
    private String name;

    public Class getClazz() {
        return clazz;
    }


    private Class clazz;

    // if true object is representation of class. If not object is representation of catalog
    private boolean isClass;

    public boolean isClass() {
        return isClass;
    }

    public String getFullPath() {
        return fullPath;
    }

    public String getName() {
        return name;
    }

    public FileFromJar(String fullPath, String name, Class clazz) {
        this.fullPath = fullPath;
        this.name = name;
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return getName();
    }
}
