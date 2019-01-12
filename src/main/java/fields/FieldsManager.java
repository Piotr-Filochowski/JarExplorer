package main.java.fields;

import javafx.scene.control.ListView;
import javassist.CtField;
import main.java.Package;


public class FieldsManager {

    private ListView<?> listOfFields;

    public FieldsManager(ListView<?> listOfMethods) {
        this.listOfFields = listOfMethods;
    }

    public void loadFiledList(Package selectedPackage) {


        CtField[] fields = selectedPackage.getCtClass().getDeclaredFields();
        MyObservableList myObservableList = new MyObservableList();
        for (CtField ctField : fields) {
            myObservableList.add(new Field(ctField));
        }
        listOfFields.setItems(myObservableList);
    }
}
