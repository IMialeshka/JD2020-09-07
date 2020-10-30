package by.it.kalashnikov.jd02_06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

class Logger {
    //    Если используем enum
//    GET;
    private final String fileName = "log.txt";
    //    Классический пример без использования enum. Использование для понимания многопоточки.начало
    private static volatile Logger logger;

    private Logger() {
    }

    static Logger getInstance() {
        Logger localLogger = Logger.logger;
        if (localLogger != null) {
            synchronized (Logger.class) {
                localLogger = Logger.logger;
                if (localLogger != null) {
                    logger = localLogger = new Logger();
                }
            }
        }
        return localLogger;
    }
//    конец

    private static String getFilename(Class<?> aClass, String simpleName) {
        String path = System.getProperty("user.dir") + File.separator + "src" + File.separator;
        path = path + aClass.getName().replace(".", File.separator);
        path = path.replace(aClass.getSimpleName(), "");
        return path + simpleName;
    }

    public void log(String text) {
        String fn = getFilename(Logger.class, this.fileName);
        try (PrintWriter printWriter = new PrintWriter(
                new FileWriter(fn, true))) {
            printWriter.println(text);
        } catch(IOException e) {
            throw  new RuntimeException(e);
        }
    }
}
