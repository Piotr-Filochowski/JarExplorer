package JarActions;

import javassist.CtClass;

public class MyPackage {
    private CtClass ctClass;
    private boolean isPackage;
    private String packageName;


    public MyPackage(String signatureName, boolean isPackage) {
        this.isPackage = isPackage;
        packageName = signatureName;

    }

    public MyPackage(String signatureName, boolean isPackage, CtClass ctClass) {
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
