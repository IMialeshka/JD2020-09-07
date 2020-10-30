package by.it.trukhanovich.calc;


import by.it.trukhanovich.calc.Report.RunnerReport;


import java.io.*;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleRunner {
    public static Date DATE_START;
    public static Date DATE_FINISH;
    public static void main(String[] args) throws CalcException {
        DATE_START=new Date();
        ConsoleRunner cs=new ConsoleRunner();
        cs.clearLogs();
        Scanner sc=new Scanner(System.in);
        Parser parser=new Parser();
        Printer printer=new Printer();
        Lang resource = Lang.LANG;
        try {
            Var.load();
        } catch (CalcException e) {
            e.printStackTrace();
        }
        for (;;){
         String expression= sc.nextLine();
         saveLogToTxt("log.txt",expression);
         if (expression.equals("ru")){
             resource.setLocale(new Locale("ru", "RU"));
             continue;
         }
         if (expression.equals("be")){
             resource.setLocale(new Locale("be", "BY"));
             continue;
         }
         if (expression.equals("en")){
             resource.setLocale(new Locale("en", "UK"));
             continue;
         }
         if (expression.equals("printvar")){
             System.out.println(Var.vars);
             continue;
         }
         if (expression.equals("sortvar")){
             System.out.println(Var.sortMap());
             continue;
         }
         if (expression.equals("end")){
             DATE_FINISH=new Date();
             break;
         }
         Var result=null;
         try {

             result=parser.calc(expression);
             saveLogToTxt("log.txt", String.valueOf(result));
         } catch (CalcException e) {
             System.out.println(e.getMessage());
             saveLogToTxt("log.txt",e.getMessage());
             Logger.getInstance();
             Logger.log(e.getMessage());
         }
            printer.print (result);

        }
        RunnerReport.main(args);

    }

    private static void saveLogToTxt (String name, String log) throws CalcException {
        String path=getPath(ConsoleRunner.class)+name;
        int rowNumberInLog =0;
        boolean flag=true;
        try (BufferedReader bf = new LineNumberReader(new FileReader(path))){
            while (bf.readLine()!=null) {

                rowNumberInLog++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (rowNumberInLog>50) flag=false;
        try (PrintWriter writer = new PrintWriter(new FileWriter(path,flag)))
        {
            writer.println(log);

        } catch (IOException e) {
            throw new CalcException(e);
        }
    }

    private static String getPath(Class<?> taskAClass) {
        String rootProject = System.getProperty("user.dir");
        String relativePath = taskAClass
                .getName()
                .replace(taskAClass.getSimpleName(), "")
                .replace(".", File.separator);
        return rootProject + File.separator + "src" + File.separator + relativePath;
    }
    private void clearLogs() throws CalcException {
        String pathLog=getPath(ConsoleRunner.class)+"log.txt";
        String pathErr=getPath(ConsoleRunner.class)+"log errror.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(pathLog,false)))
        {
            writer.println("");

        } catch (IOException e) {
            throw new CalcException(e);
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(pathErr,false)))
        {
            writer.println("");

        } catch (IOException e) {
            throw new CalcException(e);
        }


    }
}
