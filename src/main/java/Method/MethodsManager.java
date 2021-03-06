package Method;

import javafx.scene.control.ListView;
import javassist.CtMethod;
import JarActions.*;

public class MethodsManager {

    private ListView<Method> listofMethods;

    public MethodsManager(ListView<Method> listOfMethods) {
        this.listofMethods = listOfMethods;
    }

    public void loadMethodList(MyPackage selectedPackage) {
        CtMethod[] ctMethods = selectedPackage.getCtClass().getDeclaredMethods();
        MyObservableMethodList myObservableList = new MyObservableMethodList();
        for (CtMethod ctMethod : ctMethods) {
            myObservableList.add(new Method(ctMethod));
        }
        listofMethods.setItems(myObservableList);
    }
}
