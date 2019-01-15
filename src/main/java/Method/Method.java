package Method;

import javassist.CannotCompileException;
import javassist.CtMethod;
import javassist.Modifier;

import UserInterface.*;

public class Method {

    CtMethod ctMethod;

    Method(CtMethod ctMethod) {
        this.ctMethod = ctMethod;
    }

    @Override
    public String toString() {
        int mod = ctMethod.getModifiers();
        String modString = Modifier.toString(mod);
        if (modString.length() != 0) {
            return (modString + " " + ctMethod.getName());
        } else return (ctMethod.getName());
    }

    public void addCodeBeforeCall() {
        String input = PopupWindow.getInputCode("What would u like to do before " + ctMethod.getName() + "?");
        try {
            ctMethod.insertBefore(input);
        } catch (CannotCompileException e) {
            PopupWindow.displayError("ERROR", e.getMessage());
        }
    }

    public void addCodeAfterCall() {
        String input = PopupWindow.getInputCode("What would u like to do after " + ctMethod.getName() + "?");
        try {
            ctMethod.insertAfter(input);
        } catch (CannotCompileException e) {
            PopupWindow.displayError("ERROR", e.getMessage());
        }
    }

}
