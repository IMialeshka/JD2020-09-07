package by.it.trukhanovich.jd01_14;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TaskC {

    public static final String TASK_C_TXT = "resultTaskC.txt";

    public static void main(String[] args) {
        String myPackageAddress=getPath(TaskC.class);
        System.out.println(myPackageAddress);
        myPackageAddress = new File(myPackageAddress).getParent();
        System.out.println(myPackageAddress);
        File myPackage=new File(myPackageAddress);
        ArrayList <String>arrayList = new ArrayList();
        File[] files = myPackage.listFiles();
        getDirectory(arrayList, files);
        String path=getPath(TaskC.class)+TASK_C_TXT;
        printToTxt(arrayList,path);
        for (String s1 : arrayList) {
            System.out.printf("%s ", s1);
        }

    }

    private static void getDirectory(ArrayList<String> arrayList, File[] files) {
        for (File file : files) {
            if (file.isDirectory()){
                arrayList.add("dir:"+file.getName());
                getDirectory(arrayList,file.listFiles());
            }
            else arrayList.add("file:"+file.getName());
        }
    }

    private static String getPath(Class<TaskC> taskCClass) {
        String rootProject = System.getProperty("user.dir");
        String path = taskCClass.getName().
                replace(taskCClass.getSimpleName(),"").
                replace(".", File.separator);
        return rootProject+File.separator+"src"+File.separator+path;
    }
    private static void printToTxt(ArrayList <String> arrayList, String path) {
        try(PrintWriter printWriter = new PrintWriter(path))
        {
            for (String s : arrayList) {
                printWriter.printf("%s ", s);

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
