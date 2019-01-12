package main.java.fields;

import javassist.CtField;
import javassist.Modifier;

public class Field {

    CtField ctField;

    Field(CtField field) {
        this.ctField = field;
    }

    @Override
    public String toString() {
        int mod = ctField.getModifiers();
        String modString = Modifier.toString(mod);
        if (modString.length() != 0) {
            return (modString + " " + ctField.getName());
        } else return (ctField.getName());
    }

}
