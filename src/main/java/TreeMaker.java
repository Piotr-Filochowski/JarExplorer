

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javassist.CtClass;

import java.util.ArrayList;

/*
 * Class that makes Tree out of list of CtClass objects
 */
public class TreeMaker {

    static TreeView<MyPackage> treeOfPackages = new TreeView<>();
    static TreeItem<MyPackage> rootNode = new TreeItem<MyPackage>(new MyPackage("RootName", true));

    static public TreeItem<MyPackage> makeTree(ArrayList<CtClass> ctClassesList) {
        treeOfPackages.setRoot(rootNode);
        for (CtClass ctClass : ctClassesList) {
            addNewClass(ctClass);
        }

        return rootNode;
    }

    private static void addNewClass(CtClass ctClass) {
        ArrayList<String> listOfPackagesFromParent = getPackagesPathList(ctClass.getPackageName());
        TreeItem<MyPackage> tempRoot = rootNode;
        TreeItem<MyPackage> iterator;
        boolean allPackagesExist = true;
        for (String packageSignature : listOfPackagesFromParent) {
            iterator = findInChildren(tempRoot, packageSignature);

            if (iterator == null) {
                allPackagesExist = false;

            } else tempRoot = iterator;
            if (!allPackagesExist) {
                TreeItem<MyPackage> newPackage = new TreeItem<MyPackage>(new MyPackage(packageSignature, true));
                tempRoot.getChildren().add(newPackage);
                tempRoot = newPackage;
            }
        }
        addClassToTreeView(tempRoot, ctClass);
    }

    private static void addClassToTreeView(TreeItem<MyPackage> where, CtClass what) {
        MyPackage myPackage = new MyPackage(getNameOfPackage(what.getName()), false, what);
        where.getChildren().add(new TreeItem<MyPackage>(myPackage));
    }

    private static TreeItem<MyPackage> findInChildren(TreeItem<MyPackage> where, String what) {
        if (what == null || what == null) return null;
        if (where != null && where.getValue().equals(what)) {
            return where;
        }
        TreeItem<MyPackage> result = null;
        for (TreeItem<MyPackage> child : where.getChildren()) {
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