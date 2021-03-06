package by.it.hryntsaliou.jd01_10;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class PrintString {
    public static void main(String[] args) {
        Class<String> stringClass = String.class;
        Method[] methods = stringClass.getDeclaredMethods();
        for (Method method : methods) {
            int modifiers = method.getModifiers();
            StringBuilder out = new StringBuilder();
            if (!Modifier.isStatic(modifiers)) {
                out.append(method.getName());
                System.out.println(out);
            }
        }
    }
}
