package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;
import java.io.File;
import java.util.ArrayList;

import JarActions.*;
import UserInterface.*;
import Field.*;
import Method.*;

public class Controller {

    FieldsManager fieldsManager;

    MethodsManager methodsManager;

    ArrayList<CtClass> tempCtClassList = null;

    String pathToJar;

    private Stage stage;

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
        if (listOfMethods.getFocusModel() == null) return;
        Method selectedMethod = listOfMethods.getFocusModel().getFocusedItem();
        selectedMethod.addCodeAfterCall();
    }

    @FXML
    void addCodeBeforeCall(MouseEvent event) {
        if (listOfMethods.getFocusModel() == null) return;
        Method selectedMethod = listOfMethods.getFocusModel().getFocusedItem();
        selectedMethod.addCodeBeforeCall();

    }


    @FXML
    void addField(MouseEvent event) {
        // CtField.make()'
        if (treeOfClasses.getRoot() == null) return;
        MyPackage selectedMyPackage = treeOfClasses.getFocusModel().getFocusedItem().getValue();
        if (selectedMyPackage.isPackage() == true) {
            return;
        }
        String fieldCode = PopupWindow.getInputCode("Type code of Field.Field here");
        System.out.println(fieldCode);
        try {
            CtField field = CtField.make(fieldCode, selectedMyPackage.getCtClass());
            selectedMyPackage.getCtClass().addField(field);
            System.out.println(selectedMyPackage.getCtClass().getName());
            System.out.println(selectedMyPackage.getCtClass().getDeclaredField("myField").getName());
            reloadFieldAndMethodList(event);
        } catch (CannotCompileException e) {
            PopupWindow.displayError("ERROR", "/n/n" + e.getMessage());
        } catch (NotFoundException e) {
            System.out.println("fuck u");
        }
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
            selectedJarFile = fileChooseLoad();
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
            newFile = fileChooseSave();
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

    public void sendStage(Stage primaryStage) {
        this.stage = primaryStage;
    }


    public File fileChooseLoad() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Jar Files", "*.jar"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            return selectedFile;
        }

        return null;

    }

    public File fileChooseSave() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Jar");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Jar Files", "*.jar"));
        File file = fileChooser.showSaveDialog(stage);
        System.out.println("Name: " + file.getName() + ", Path: " + file.getPath());
        return file;
    }
}
