package main.java;


import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javassist.CtClass;

import java.util.ArrayList;

/*
 * Class that makes Tree out of list of CtClass objects
 */
public class TreeMaker {
    private final Node rootIcon = new ImageView(
            new Image(getClass().getResourceAsStream("folder_16.png"))
    );
    static TreeView<Package> treeOfPackages = new TreeView<>();
    static TreeItem<Package> rootNode = new TreeItem<Package>(new Package("RootName", true));

    static public TreeItem<Package> makeTree(ArrayList<CtClass> ctClassesList) {
        treeOfPackages.setRoot(rootNode);
        for (CtClass ctClass : ctClassesList) {
            addNewClass(ctClass);
        }

        return rootNode;
    }

    private static void addNewClass(CtClass ctClass) {
        ArrayList<String> listOfPackagesFromParent = getPackagesPathList(ctClass.getPackageName());
        TreeItem<Package> tempRoot = rootNode;
        TreeItem<Package> iterator;
        boolean allPackagesExist = true;
        for (String packageSignature : listOfPackagesFromParent) {
            iterator = findInChildren(tempRoot, packageSignature);

            if (iterator == null) {
                allPackagesExist = false;

            } else tempRoot = iterator;
            if (!allPackagesExist) {
                TreeItem<Package> newPackage = new TreeItem<Package>(new Package(packageSignature, true));
                tempRoot.getChildren().add(newPackage);
                tempRoot = newPackage;
            }
        }
        addClassToTreeView(tempRoot, ctClass);
    }

    private static void addClassToTreeView(TreeItem<Package> where, CtClass what) {
        Package myPackage = new Package(getNameOfPackage(what.getName()), false, what);
        where.getChildren().add(new TreeItem<Package>(myPackage));
    }

    private static TreeItem<Package> findInChildren(TreeItem<Package> where, String what) {
        if (what == null || what == null) return null;
        if (where != null && where.getValue().equals(what)) {
            return where;
        }
        TreeItem<Package> result = null;
        for (TreeItem<Package> child : where.getChildren()) {
            String temp = child.getValue().toString();
            if (temp.equals(what)) {
                if (where.getValue().isPackage() == true) {
                    result = child;
                    break;
                }
            }
        }
        return result;
    }

    private static ArrayList<String> getPackagesPathList(String packageName) {
        // TODO ->      com.diamond.ian.javagame.entities -> { com; diamond; ian; javagame; entities; }
        ArrayList<String> packagesPathList = new ArrayList<String>();
        String temp;
        String tempStringTwo = packageName;
        while (true) {
            if (!tempStringTwo.contains(".")) {
                packagesPathList.add(tempStringTwo);
                break;
            }
            temp = tempStringTwo.substring(0, tempStringTwo.indexOf("."));
            packagesPathList.add(temp);
            tempStringTwo = tempStringTwo.replace(temp, "");
            tempStringTwo = tempStringTwo.substring(1);
            if (temp == null) {
                break;
            }
        }
        return packagesPathList;
    }

    private static String getNameOfPackage(String fullClassName) {
        // com.als.name ---> name
        if (fullClassName == null) return null;
        return fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
    }

}