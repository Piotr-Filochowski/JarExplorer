package JarActions;

import javafx.scene.control.TreeView;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.ClassFile;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;


public class JarViewer {

    TreeView<MyPackage> treeView;
    ClassPool classPool;

    public JarViewer(ClassPool classPool) {
        this.classPool = classPool;
        treeView = new TreeView<>();
    }

    public ArrayList<String> getClassNamesList(String jarPath) {
        ArrayList<String> listOfClasses = new ArrayList<>();
        JarInputStream jarInputStream;
        try {
            jarInputStream = new JarInputStream(new FileInputStream(jarPath));
            JarEntry temp = jarInputStream.getNextJarEntry();
            while (true) {
                if (temp == null) {
                    break;
                }
                if (temp.getName().endsWith(".class")) {
                    listOfClasses.add(getClassName(temp.getName()));
                    System.out.println(temp.getName());
                }
                temp = jarInputStream.getNextJarEntry();
            }
            jarInputStream.close();

        } catch (Exception e) {
            System.out.println("Oops.. Encounter an issue while parsing jar" + e.toString());
        } finally {

        }
        return listOfClasses;
    }


    public static String getClassName(String classNameWithPath) {
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
            System.out.println("I couldnt find this ****");
        } finally {
            return ctClassesList;
        }
    }

    public void makeTree(ArrayList<CtClass> ctClassesList) {

    }
}