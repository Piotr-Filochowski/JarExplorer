package main.java;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * @author Crunchify.com
 */

public class JarViewer {

    TreeView<FileFromJar> treeView;

    public JarViewer() {
        treeView = new TreeView<>();
        TreeItem<FileFromJar> rootTreeItem = new TreeItem<FileFromJar>(new FileFromJar(null, "Jar Name", null));
        treeView.setRoot(rootTreeItem);
    }

    private final Node rootIcon = new Rectangle(20, 20);

    public ArrayList<FileFromJar> generateClassTree(String crunchifyJarName) {
        ArrayList<FileFromJar> listOfClasses = new ArrayList<>();
        try {

            JarInputStream crunchifyJarFile = new JarInputStream(new FileInputStream(crunchifyJarName));

            while (createTreeItems(crunchifyJarFile.getNextJarEntry())) ;

        } catch (Exception e) {
            System.out.println("Oops.. Encounter an issue while parsing jar" + e.toString());
        }
        return listOfClasses;
    }

    public void loadTreeView(AnchorPane anchorPane) {

        anchorPane.getChildren().add(treeView);
        treeView.prefWidthProperty().bind(anchorPane.widthProperty());
        treeView.prefHeightProperty().bind(anchorPane.heightProperty());
    }

    public boolean createTreeItems(JarEntry crunchifyJar) {
        String tempClassName;
        String tempClassPath;
        TreeItem<FileFromJar> tempTreeItem;
        TreeItem<FileFromJar> tempNode = treeView.getRoot();
        if (crunchifyJar == null) {
            return false;
        }
        tempClassPath = crunchifyJar.getName().replaceAll("/", "\\.");
        tempClassName = tempClassPath.substring(0, tempClassPath.lastIndexOf('.'));
        tempTreeItem = new TreeItem<FileFromJar>(new FileFromJar(tempClassPath, tempClassName, crunchifyJar.getClass()));
        tempNode.getChildren().add(tempTreeItem);
        // Class file -> create Trea Leaf
        if ((crunchifyJar.getName().endsWith(".class"))) {
//            if(){
//
//            }
        } else { // dir file -> create Trea node
            tempNode = tempTreeItem;
        }
        return true;

    }

}