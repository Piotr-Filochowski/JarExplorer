package JarActions;

import javassist.CtClass;

import java.io.ByteArrayInputStream;
import java.nio.file.*;

public class JarExporter {
    /*
     * It's functionality is to export changes to new Jar file
     * */


    public void exportJar(CtClass[] classes, String pathToJar, String destination) {
        try {
            Files.copy(Paths.get(pathToJar), Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
            Path pth;
            Path directory;
            FileSystem zips = FileSystems.newFileSystem(Paths.get(destination), null);
            for (CtClass c : classes) {
                c.defrost();
                if (c.getPackageName() != null) {
                    directory = zips.getPath(c.getPackageName().replace(".", "/"));
                    if (Files.notExists(directory)) {
                        Files.createDirectory(directory);
                    }
                }
                pth = zips.getPath(c.getName().replace(".", "/") + ".class");
                Files.copy(new ByteArrayInputStream(c.toBytecode()), pth, StandardCopyOption.REPLACE_EXISTING);
                c.defrost();
            }
            zips.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
