import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javassist.CtClass;

import java.io.File;
import java.util.ArrayList;

public class Controller {

    FieldsManager fieldsManager;

    MethodsManager methodsManager;

    ArrayList<CtClass> tempCtClassList = null;

    String pathToJar;

    @FXML
    private TreeView<MyPackage> treeOfClasses;

    @FXML
    private ListView<Field> listOfFields;

    @FXML
    private ListView<Method> listOfMethods;

    @FXML
    void addClass(MouseEvent event) {
        // TODO
    }

    @FXML
    void addCodeAfterCall(MouseEvent event) {
        // TODO
    }

    @FXML
    void addCodeBeforeCall(MouseEvent event) {
        if (listOfMethods.getFocusModel() == null) return;
        Method selectedMethod = listOfMethods.getFocusModel().getFocusedItem();
        selectedMethod.addCodeBeforeCall();

    }


    @FXML
    void addField(MouseEvent event) {
        // TODO
    }

    @FXML
    void addMethod(MouseEvent event) {
        // TODO
    }

    @FXML
    void addPackage(MouseEvent event) {
        // TODO
    }

    @FXML
    void deleteAddedMethod(MouseEvent event) {
        // TODO
    }

    @FXML
    void deleteAddedPackage(MouseEvent event) {
        // TODO
    }

    @FXML
    void deleteClass(MouseEvent event) {
        // TODO
    }

    @FXML
    void deleteField(MouseEvent event) {
        // TODO
    }


    @FXML
    void openFileAndLoadIt() {
        File selectedJarFile;
        try {
            fieldsManager = new FieldsManager(listOfFields);
            methodsManager = new MethodsManager(listOfMethods);
            selectedJarFile = Main.fileChoose();
            JarViewer jarViewer = new JarViewer();
            pathToJar = selectedJarFile.getPath();
            ArrayList<String> classNamesList = jarViewer.getClassNamesList(pathToJar);
            tempCtClassList = jarViewer.getClasses(classNamesList, selectedJarFile.getPath());
            TreeMaker treeMaker = new TreeMaker();
            TreeItem<MyPackage> tempTreeRoot = treeMaker.makeTree(tempCtClassList);
            treeOfClasses.setRoot(tempTreeRoot);
        } catch (NullPointerException e) {
            return; // user didnt choose any file
        }
    }

    @FXML
    void reloadFieldAndMethodList(MouseEvent event) {
        if (treeOfClasses.getRoot() == null) return;
        MyPackage selectedMyPackage = treeOfClasses.getFocusModel().getFocusedItem().getValue();
        if (selectedMyPackage.isPackage() == true) {
            return;
        } else {
            fieldsManager.loadFiledList(selectedMyPackage);
            methodsManager.loadMethodList(selectedMyPackage);
        }
    }

    @FXML
    void saveFile() {
        File newFile;
        if (pathToJar == null) return;
        try {
            newFile = Main.saveFile();
            JarExporter jarExporter = new JarExporter();
            CtClass[] array = new CtClass[tempCtClassList.size()];
            tempCtClassList.toArray(array);
            jarExporter.exportJar(array, pathToJar, newFile.getPath());
        } catch (NullPointerException e) {
            return; // user didnt choose any file
        }

    }

    @FXML
    void quit() {
        System.exit(0);
    }

    @FXML
    void initialize() {
//        assert openFileAndLoadIt != null : "fx:id=\"openFileAndLoadIt\" was not injected: check your FXML file 'mainScreen.fxml'.";
//        assert saveFile != null : "fx:id=\"saveFile\" was not injected: check your FXML file 'mainScreen.fxml'.";
//        assert x1 != null : "fx:id=\"x1\" was not injected: check your FXML file 'mainScreen.fxml'.";
//        assert x2 != null : "fx:id=\"x2\" was not injected: check your FXML file 'mainScreen.fxml'.";
//        assert x3 != null : "fx:id=\"x3\" was not injected: check your FXML file 'mainScreen.fxml'.";
//        assert x4 != null : "fx:id=\"x4\" was not injected: check your FXML file 'mainScreen.fxml'.";

    }
}
