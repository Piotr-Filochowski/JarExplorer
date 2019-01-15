import javafx.scene.control.ListView;
import javassist.CtField;


public class FieldsManager {

    private ListView<Field> listOfFields;

    public FieldsManager(ListView<Field> listOfMethods) {
        this.listOfFields = listOfMethods;
    }

    public void loadFiledList(MyPackage selectedPackage) {
        CtField[] fields = selectedPackage.getCtClass().getDeclaredFields();
        MyObservableFieldsList myObservableList = new MyObservableFieldsList();
        for (CtField ctField : fields) {
            myObservableList.add(new Field(ctField));
        }
        listOfFields.setItems(myObservableList);
    }
}
