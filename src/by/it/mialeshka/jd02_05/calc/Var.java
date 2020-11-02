package by.it.mialeshka.jd02_05.calc;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

abstract class Var implements Operation {
    private static final Map<String, Var> vars = new HashMap<>();

    private static String dir(){
        return System.getProperty("user.dir") + File.separator + "src" + File.separator
                + Var.class.getName().replace(Var.class.getSimpleName(), "").replace(".", File.separator);
    }

    static Var saveVar(String name, Var var){
        vars.put(name, var);
        return var;
    }
    static void saveInFile() throws CalcException {
        try(PrintWriter fileToSave = new PrintWriter(new FileWriter(dir() + File.separator + "vars.txt."))){
            for (String key: vars.keySet()) {
                fileToSave.println(key+"="+vars.get(key));
            }
        } catch (IOException e) {
            throw new CalcException(ErrMesages.res.get(ErrMesages.VarSave));
        }
    }

    static void getFileVar() throws CalcException {
        String str;
        if(new File(dir() +  File.separator + "vars.txt.").exists()) {
            try (BufferedReader read = new BufferedReader(new FileReader(dir() + File.separator + "vars.txt."))) {
                while ((str = read.readLine()) != null) {
                    String[] parts = str.split(Patterns.OPERATION, 2);
                    saveVar(parts[0], createVar(parts[1]));
                }
            } catch (IOException e) {
                throw new CalcException(ErrMesages.res.get(ErrMesages.rideFile));
            }
        }
    }

    static void printMap(){
        for (String key : vars.keySet()){
            System.out.println(key+"="+vars.get(key));
        }
    }

    static void printSortMap(){
        Map<String, Var> sortVars = new TreeMap<>();
        for (String key: vars.keySet()
             ) {
            sortVars.put(key, vars.get(key));
        }

        for (String key : sortVars.keySet()){
            System.out.println(key+"="+sortVars.get(key));
        }
    }



    public String toString() {
        return ErrMesages.res.get(ErrMesages.var);
    }

    @Override
    public Var add(Var other)  throws CalcException{
        throw new CalcException(String.format(ErrMesages.res.get(ErrMesages.varAdd), this, other));
    }

    @Override
    public Var sub(Var other)  throws CalcException{
        throw  new CalcException(String.format(ErrMesages.res.get(ErrMesages.varSub), this, other));
    }

    @Override
    public Var mul(Var other)  throws CalcException{
        throw new CalcException(String.format(ErrMesages.res.get(ErrMesages.varMul), this, other));
    }

    @Override
    public Var div(Var other)  throws CalcException{
        throw new CalcException(String.format(ErrMesages.res.get(ErrMesages.varDiv), this, other));
    }

    static Var createVar(String strVar) throws CalcException{
        if (strVar.matches(Patterns.SCALAR)) {
            return new Scalar(strVar);
        }
        else if (strVar.matches(Patterns.VECTOR)) {
            return new Vector(strVar);
        }
        else if (strVar.matches(Patterns.MATRIX)) {
            return new Matrix(strVar);
        }
        else if(vars.containsKey(strVar)){
            return vars.get(strVar);
        }
        else {
            throw new CalcException(String.format(ErrMesages.res.get(ErrMesages.varUnknown), strVar));
        }
    }
}
