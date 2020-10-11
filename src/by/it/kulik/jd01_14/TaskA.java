package by.it.kulik.jd01_14;

import javafx.concurrent.Task;

import java.io.*;

public class TaskA {
    private static String dir(Class<?> cl) {
        String path = System.getProperty("user.dir") + File.separator + "src" + File.separator;
        String classDir = cl.getName().replace(cl.getSimpleName(), "").replace(".", File.separator);
        return path + classDir;
    }

    public static void main(String[] args) {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(dir(TaskA.class)+"dataTaskA.bin"))
            );
            for (int i = 0; i < 20; i++) {
                dos.writeInt((int) (Math.random() * 100+10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try (DataInputStream input=new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(dir(TaskA.class)+"dataTaskA.bin")));
             PrintWriter out2=new PrintWriter(new FileWriter(dir(TaskA.class)+"resultTaskA.txt"))
        ){
            double sum=0;
            double count=0;
            while (input.available()>0){
                int i= input.readInt();
                System.out.print(i+" ");
                sum+=i;
                count++;
                }
            System.out.println("\navg="+sum/count);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
