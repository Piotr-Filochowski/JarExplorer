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
import main.java.fields.FieldsManager;
import main.java.methods.MethodsManager;

import java.io.File;
import java.util.ArrayList;


public class Controller {

    FieldsManager fieldsManager;
    MethodsManager methodsManager;

    @FXML
    private MenuItem openFile;

    @FXML
    private MenuItem saveFile;

    @FXML
    private MenuItem quit;

    @FXML
    private TreeView<Package> treeOfClasses;

    @FXML
    private ListView<CtField> listOfFields;

    @FXML
    private ListView<CtMethod> listOfMethods;

    @FXML
    void openFileAndLoadIt() {
        fieldsManager = new FieldsManager(listOfFields);
        methodsManager = new MethodsManager(listOfMethods);
        File selectedJarFile = Main.fileChoose();
        JarViewer jarViewer = new JarViewer();
        ArrayList<String> temp = jarViewer.getClassNamesList(selectedJarFile.getPath());
        ArrayList<CtClass> tempCtClassList = jarViewer.getClasses(temp, selectedJarFile.getPath());
        TreeItem<Package> tempTreeRoot = TreeMaker.makeTree(tempCtClassList);
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
