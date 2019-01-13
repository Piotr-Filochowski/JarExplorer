package main.java.fields;

import javafx.scene.control.ListView;
import javassist.CtField;
import main.java.Package;


public class FieldsManager {

    private ListView<Field> listOfFields;

    public FieldsManager(ListView<Field> listOfMethods) {
        this.listOfFields = listOfMethods;
    }

    public void loadFiledList(Package selectedPackage) {


        CtField[] fields = selectedPackage.getCtClass().getDeclaredFields();
        MyObservableFieldsList myObservableList = new MyObservableFieldsList();
        for (CtField ctField : fields) {
            myObservableList.add(new Field(ctField));
        }
        listOfFields.setItems(myObservableList);
    }
}
