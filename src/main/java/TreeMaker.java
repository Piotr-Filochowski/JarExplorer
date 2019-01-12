package main.java;


import com.sun.istack.internal.Nullable;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javassist.CtClass;

import java.util.ArrayList;
import java.util.TreeMap;

/*
 * Class that makes Tree out of list of CtClass objects
 */
public class TreeMaker {

    static TreeMap<String, Package> treeOfPackages = new TreeMap<String, Package>();


    static public TreeItem<CustomTreeItem> makeTree(ArrayList<CtClass> ctClassesList) {
        String tempPackageName;
        Package myPackage;
        treeOfPackages.put("RootNode", new Package(null, "Root.Node"));
        for (CtClass ctClass : ctClassesList) {
            addNewClass(ctClass);
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

    private static void createNewPackage(String fullPackageName) {
        String onlyName = getNameOfPackage(fullPackageName);
        String parentPackageName = getFullNameAfterLastDot(fullPackageName);
        Package where = whereShouldIAddClass(parentPackageName);
        if (where != null) {
            addPackageToTree(new Package(where, fullPackageName), where);
        } else {
            if (parentPackageName == null) {
                // Add to root
                Package root = treeOfPackages.get("RootNode");
                addPackageToTree(new Package(root, onlyName), root);
                return;
            }
            createNewPackage(parentPackageName);
        }
    }

    private static void addPackageToTree(Package myPackage, Package where) {
        treeOfPackages.get(where).getPackagesContent().add(myPackage);
    }

    private static String getNameOfPackage(String fullClassName) {
        // com.als.name ---> name
        return fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
    }

    private static String getFullNameAfterLastDot(String fullClassName) {
        // com.als.name ---> com.als
        try {
            return fullClassName.substring(0, fullClassName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            return null;
        }
    }

    @Nullable
    private static Package whereShouldIAddClass(String fullPackageName) {
        if (fullPackageName == null) return null;
        if (treeOfPackages.containsKey(fullPackageName)) {
            return treeOfPackages.get(fullPackageName);
        } else return null;
    }
}