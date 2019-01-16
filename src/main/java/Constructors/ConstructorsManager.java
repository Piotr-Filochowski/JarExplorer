package Constructors;

import JarActions.MyPackage;
import javafx.scene.control.ListView;
import javassist.CtConstructor;

public class ConstructorsManager {

    private ListView<Constructor> listOfConstructors;

    public ConstructorsManager(ListView<Constructor> listOfConstructors) {
        this.listOfConstructors = listOfConstructors;
    }

    public void loadConstructorList(MyPackage selectedPackage) {
        CtConstructor[] ctConstructors = selectedPackage.getCtClass().getDeclaredConstructors();
        MyObservableConstructorsList myObservableConstructorsList = new MyObservableConstructorsList();


        for (CtConstructor ctConstructor : ctConstructors) {
            myObservableConstructorsList.add(new Constructor(selectedPackage.getCtClass(), ctConstructor));
        }
        listOfConstructors.setItems(myObservableConstructorsList);
    }
}
