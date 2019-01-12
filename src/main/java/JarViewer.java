package main.java;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;


public class JarViewer {

    TreeView<Package> treeView;

    public JarViewer() {
        treeView = new TreeView<>();
    }

    public ArrayList<String> getClassNamesList(String jarPath) {
        ArrayList<String> listOfClasses = new ArrayList<>();
        try {
            JarInputStream jarInputStream = new JarInputStream(new FileInputStream(jarPath));
            JarEntry temp = jarInputStream.getNextJarEntry();
            while (true) {
                if (temp == null) {
                    break;
                }
                if (temp.getName().endsWith(".class")) {
                    listOfClasses.add(getClassName(temp.getName()));
                }
                temp = jarInputStream.getNextJarEntry();
            }

        } catch (Exception e) {
            System.out.println("Oops.. Encounter an issue while parsing jar" + e.toString());
        }
        return listOfClasses;
    }

    public void loadTreeView(AnchorPane anchorPane, TreeItem<Package> root) {
        treeView.setRoot(root);
        anchorPane.getChildren().add(treeView);
        treeView.prefWidthProperty().bind(anchorPane.widthProperty());
        treeView.prefHeightProperty().bind(anchorPane.heightProperty());
    }

    public String getClassName(String classNameWithPath) {
        String temp = classNameWithPath.replaceAll("/", "\\.");
        temp = temp.substring(0, temp.lastIndexOf('.'));
        return temp;

    }

    public ArrayList<CtClass> getClasses(ArrayList<String> listOfClasses, String jarPath) {
        ArrayList<CtClass> ctClassesList = new ArrayList<CtClass>();
        try {
            ClassPool classPool = new ClassPool(true);
            classPool.insertClassPath(jarPath);
            for (String tempClassName : listOfClasses) {
                ctClassesList.add(classPool.get(tempClassName));
            }
        } catch (NotFoundException e) {
            System.out.println("I couldnt find this crap");
        } finally {
            return ctClassesList;
        }
    }

    public void makeTree(ArrayList<CtClass> ctClassesList) {

    }
}