package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javassist.CtClass;
import main.java.CustomTreeItem;
import main.java.JarViewer;
import main.java.Main;
import main.java.Package;
import main.java.TreeMaker;

import java.io.File;
import java.util.ArrayList;


public class Controller {


    @FXML
    private MenuItem openFile;

    @FXML
    private MenuItem saveFile;

    @FXML
    private Font x1;

    @FXML
    private Color x2;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    void openFileAndLoadIt() {
        File selectedJarFile = Main.fileChoose();
        JarViewer jarViewer = new JarViewer();
        ArrayList<String> temp = jarViewer.getClassNamesList(selectedJarFile.getPath());
        ArrayList<CtClass> tempCtClassList = jarViewer.getClasses(temp, selectedJarFile.getPath());
        TreeItem<Package> tempTreeRoot = TreeMaker.makeTree(tempCtClassList);
        jarViewer.loadTreeView(anchorPane, tempTreeRoot);
        Main.stage.show();
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
