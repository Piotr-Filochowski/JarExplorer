package main.java;


import com.sun.istack.internal.Nullable;
import javafx.scene.control.TreeItem;
import javassist.CtClass;

import java.util.ArrayList;
import java.util.TreeMap;

/*
 * Class that makes Tree out of list of CtClass objects
 */
public class TreeMaker {

    static TreeMap<String, Package> treeOfPackages = new TreeMap<String, Package>();


    static public TreeItem<CustomTreeItem> makeTree(ArrayList<CtClass> ctClassesList) {
        String tempPackage;
        for (CtClass ctClass : ctClassesList) {

            tempPackage = ctClass.getPackageName();

        }
        return null;
    }

    private static void addNewClass(CtClass ctClass) {
        Package tempPackage = whereShouldIAddClass(ctClass.getPackageName());
        if (tempPackage != null) {
            tempPackage.getClassContent().add(ctClass);
        } else {
            createNewPackage(ctClass.getPackageName());
        }
    }

    private static void createNewPackage(String fullClassName) {
        String packageName = getNameOfPackage(fullClassName);
        String parentName = getFullNameAfterLastDot(fullClassName);
        Package where = whereShouldIAddClass(parentName);
        if (where != null) {
            // TODO add here
            treeOfPackages.get(where).getPackagesContent().add(new Package(where, fullClassName));
        } else {
            createNewPackage(parentName);
        }
    }

    private static String getNameOfPackage(String fullClassName) {
        // TODO -> com.als.coock ---> coock
        return null;
    }

    private static String getFullNameAfterLastDot(String fullClassName) {
        // TODO com.als.coock ---> com.als
        return null;
    }

    @Nullable
    private static Package whereShouldIAddClass(String fullPackageName) {
        if (treeOfPackages.containsKey(fullPackageName)) {
            return treeOfPackages.get(fullPackageName);
        } else return null;
    }
}


/*
 *
 * */