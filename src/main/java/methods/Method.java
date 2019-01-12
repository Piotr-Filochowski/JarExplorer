package main.java.methods;


import javassist.CtMethod;
import javassist.Modifier;

public class Method {

    CtMethod ctMethod;

    Method(CtMethod ctMethod) {
        this.ctMethod = ctMethod;
    }

    @Override
    public String toString() {
        int mod = ctMethod.getModifiers();
        String modString = Modifier.toString(mod);
        if (modString.length() != 0) {
            return (modString + " " + ctMethod.getName());
        } else return (ctMethod.getName());
    }
}
