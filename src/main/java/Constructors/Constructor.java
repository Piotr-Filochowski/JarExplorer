package Constructors;

import JarActions.TreeMaker;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.NotFoundException;

public class Constructor {
    CtClass parent;
    CtConstructor constructor;
    String name;

    Constructor(CtClass ctClass, CtConstructor constructor) {
        this.parent = ctClass;
        this.constructor = constructor;
        makeName();
    }

    void makeName() {
        name = (TreeMaker.getNameOfPackage(constructor.getName()) + "( ");
        try {
            CtClass[] ctClasses = constructor.getParameterTypes();

            for (int i = 0; i < ctClasses.length; i++) {
                name += ctClasses[i].getName() + ", ";
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        name += ")";
    }

    @Override
    public String toString() {
        return name;
    }
}