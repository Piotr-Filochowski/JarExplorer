package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import main.java.*;
import main.java.Package;
import main.java.fields.Field;
import main.java.fields.FieldsManager;
import main.java.methods.Method;
import main.java.methods.MethodsManager;

import java.io.File;
import java.util.ArrayList;


public class Controller {

    FieldsManager fieldsManager;

    MethodsManager methodsManager;

    @FXML
    private TreeView<Package> treeOfClasses;

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
        Package selectedPackage = treeOfClasses.getFocusModel().getFocusedItem().getValue();
        Method selectedMethod = listOfMethods.getFocusModel().getFocusedItem();
        if (selectedPackage.isPackage() == true) {
            return;
        } else {
            selectedMethod.addCodeBegoreCall();
        }
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
        fieldsManager = new FieldsManager(listOfFields);
        methodsManager = new MethodsManager(listOfMethods);
        File selectedJarFile = Main.fileChoose();
        JarViewer jarViewer = new JarViewer();
        ArrayList<String> temp = jarViewer.getClassNamesList(selectedJarFile.getPath());
        ArrayList<CtClass> tempCtClassList = jarViewer.getClasses(temp, selectedJarFile.getPath());
        TreeMaker treeMaker = new TreeMaker();
        TreeItem<Package> tempTreeRoot = treeMaker.makeTree(tempCtClassList);
        treeOfClasses.setRoot(tempTreeRoot);
    }

    @FXML
    void reloadFieldAndMethodList(MouseEvent event) {
        if (treeOfClasses.getRoot() == null) return;
        Package selectedPackage = treeOfClasses.getFocusModel().getFocusedItem().getValue();
        if (selectedPackage.isPackage() == true) {
            return;
        } else {
            fieldsManager.loadFiledList(selectedPackage);
            methodsManager.loadMethodList(selectedPackage);
        }
    }

    @FXML
    void saveFile() {

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
