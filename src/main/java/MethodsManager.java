package main.java;

import javafx.scene.control.ListView;
import javassist.CtMethod;

public class MethodsManager {

    private ListView<CtMethod> listofMethods;

    public MethodsManager(ListView<CtMethod> listOfMethods) {
        this.listofMethods = listOfMethods;
    }

    public void loadMethodList(Package selectedPackage) {

    }
}
