package Controllers;

import Constructors.Constructor;
import Constructors.ConstructorsManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javassist.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import JarActions.*;
import UserInterface.*;
import Field.*;
import Method.*;
import javassist.bytecode.ClassFile;

public class Controller {

    FieldsManager fieldsManager;

    MethodsManager methodsManager;

    ConstructorsManager constructorsManager;

    ArrayList<CtClass> tempCtClassList = null;

    String pathToJar;

    ClassPool classPool;

    private Stage stage;

    @FXML
    private TreeView<MyPackage> treeOfClasses;

    @FXML
    private ListView<Field> listOfFields;

    @FXML
    private ListView<Method> listOfMethods;

    @FXML
    private ListView<Constructor> listOfConstructors;

    @FXML
    void addClass(MouseEvent event) {
        // TODO
    }

    @FXML
    void addCodeAfterCall(MouseEvent event) {
        if (treeOfClasses.getRoot() == null) return;
        if (listOfMethods.getFocusModel() == null) return;
        Method selectedMethod = listOfMethods.getFocusModel().getFocusedItem();
        selectedMethod.addCodeAfterCall();
    }

    @FXML
    void addCodeBeforeCall(MouseEvent event) {
        if (treeOfClasses.getRoot() == null) return;
        if (listOfMethods.getFocusModel() == null) return;
        Method selectedMethod = listOfMethods.getFocusModel().getFocusedItem();
        selectedMethod.addCodeBeforeCall();

    }


    @FXML
    void addField(MouseEvent event) {
        if (treeOfClasses.getRoot() == null) return;
        MyPackage selectedMyPackage = treeOfClasses.getFocusModel().getFocusedItem().getValue();
        if (selectedMyPackage.isPackage() == true) {
            return;
        }
        String fieldCode = PopupWindow.getInputCode("Type code of Field.Field here");

        try {
            CtField field = CtField.make(fieldCode, selectedMyPackage.getCtClass());
            selectedMyPackage.getCtClass().addField(field);
            reloadFieldAndMethodList(event);
        } catch (CannotCompileException e) {
            PopupWindow.displayError("ERROR", "/n/n" + e.getMessage());
        }
    }

    @FXML
    void addMethod(MouseEvent event) {
        if (treeOfClasses.getRoot() == null) return;
        MyPackage selectedMyPackage = treeOfClasses.getFocusModel().getFocusedItem().getValue();
        if (selectedMyPackage.isPackage() == true) {
            return;
        }
        if (treeOfClasses.getRoot() == null) return;
        String methodCode = PopupWindow.getInputCode("Put Method code here");
        try {
            CtMethod method = CtMethod.make(methodCode, selectedMyPackage.getCtClass());
            selectedMyPackage.getCtClass().addMethod(method);
            reloadFieldAndMethodList(event);
        } catch (CannotCompileException e) {
            PopupWindow.displayError("ERROR", "/n/n" + e.getMessage());
        }
    }

    @FXML
    void addPackage(MouseEvent event) {
//        if (treeOfClasses.getRoot() == null) return;
//        try{
//            classPool.insertClassPath("C:\\Users\\Piotr\\Documents\\Studia\\jython2.5.3\\jython.jar");
//        } catch (Exception e){
//            e.printStackTrace();
//        }
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
            classPool = new ClassPool(true);
            constructorsManager = new ConstructorsManager(listOfConstructors);
            fieldsManager = new FieldsManager(listOfFields);
            methodsManager = new MethodsManager(listOfMethods);
            selectedJarFile = popupLoadFile();
            JarViewer jarViewer = new JarViewer(classPool);
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
            constructorsManager.loadConstructorList(selectedMyPackage);
        }
    }

    @FXML
    void saveFile() {
        if (treeOfClasses.getRoot() == null) return;
        File newFile;
        if (pathToJar == null) return;
        try {
            newFile = popupSaveFile();
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
    private Button addClass;
    @FXML
    private Button deleteClass;
    @FXML
    private Button addPackage;
    @FXML
    private Button deletePackage;
    @FXML
    private Button deleteField;
    @FXML
    private Button deleteMethod;

    @FXML
    void initialize() {
        addPackage.setDisable(true);
        addClass.setDisable(true);
        deleteClass.setDisable(true);
        addPackage.setDisable(true);
        deletePackage.setDisable(true);
        deleteField.setDisable(true);
        deleteMethod.setDisable(true);
    }

    public void sendStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public File popupLoadFile() {
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

    public File popupSaveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Jar");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Jar Files", "*.jar"));
        File file = fileChooser.showSaveDialog(stage);
        System.out.println("Name: " + file.getName() + ", Path: " + file.getPath());
        return file;
    }
}
