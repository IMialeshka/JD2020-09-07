package by.it.trukhanovich.jd01_08;


import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Matrix extends Var {
     private double [][] value;
    public double[][] getValue() {
        return value;
    }

    public Matrix(double[][] value) {
        this.value = Arrays.copyOf(value, value.length);
        for (int i = 0; i < this.value.length; i++) {
            for (int j = 0; j < this.value[i].length; j++) {
                this.value[i][j]=value[i][j];
            }
        }
    }
    public Matrix(Matrix matrix) {
        this.value = matrix.value;
    }

    public Matrix(String strMatrix) {
        StringBuilder sb= new StringBuilder(strMatrix);
        int row = findRow(sb);
        int collum = findCollum(sb);
        double[][] matrix=new double[row][(((collum)/row)+1)];
        this.value= matrix;
        getArrayStrigToDouble(sb,matrix);
    }



    private void getArrayStrigToDouble(StringBuilder sb, double[][] matrix)
    {
        Pattern p3= Pattern.compile("(\\d+\\.\\d+)|(\\d+)");
        Matcher m3= p3.matcher(sb);
        int row=0;
        int collum=0;
        while (m3.find()) {
            matrix[row][collum]=Double.parseDouble(m3.group());
            if (collum<matrix[row].length-1) collum++;
            else collum=0;
            if (collum==0) row++;}
    }
    private int findCollum(StringBuilder sb) {
        Pattern p2= Pattern.compile("\\d,");
        Matcher m2= p2.matcher(sb);
        int j=0;
        while (m2.find()){j++;}
        return j;
    }

    private int findRow(StringBuilder sb) {
        Pattern p1= Pattern.compile("(\\}\\, \\{)|(\\}\\,\\{)");
        Matcher m1= p1.matcher(sb);
        int i=1;
        while (m1.find()){i++;}
        return i;
    }
    @Override
    public String getYourClass(Var other) {
        return "Matrix";
    }

    @Override
    public Var add(Var other) {
        if (other.getYourClass(other)=="Matrix"){
            this.value=value;
            double [][] s1=((Matrix) other).getValue();
            double [][] result=new double[value.length][value[0].length];
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    result [i][j]=value[i][j]+s1[i][j];
                }
            }
            return new Matrix(result);
        }
        else if (other instanceof Vector){  return super.add(other);}
        else if (other instanceof Scalar) {
            double[][] res = Arrays.copyOf(value, value.length);
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] = value[i][j] + ((Scalar) other).getValue();
                }
            }
            return new Matrix(res);
        }
        else return super.add(other);
    }
    @Override
    public Var sub(Var other) {
        if (other instanceof Matrix){
            this.value=value;
            double [][] s1=((Matrix) other).getValue();
            double[][] result=new double[value.length][value[0].length];
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    result [i][j]=value[i][j]-s1[i][j];
                }
            }
            return new Matrix(result);
        }
        else if (other instanceof Vector){  return super.sub(other);}
        else if (other instanceof Scalar) {
            double[][] res = Arrays.copyOf(value, value.length);
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] = value[i][j] - ((Scalar) other).getValue();
                }
            }
            return new Matrix(res);
        }
        else return super.sub(other);
    }


    @Override
    public Var mul(Var other) {
        if (other instanceof Scalar) {
            double[][] res = Arrays.copyOf(value, value.length);
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] = value[i][j] * ((Scalar) other).getValue();
                }
            }
            return new Matrix(res);
        } else if (other instanceof Vector) {
            double result[] = new double[value.length];
            double[] vetc = Arrays.copyOf(((Vector) other).getValue(), ((Vector) other).getValue().length);
            for (int i = 0; i < value.length; i++) {
                for (int j = 0; j < value[i].length; j++) {
                    result[i] = result[i] + value[i][j] * vetc[j];
                }
            }
            return new Vector(result);
        }
        else  if (other instanceof Matrix){
            double[][] matrixLeft = this.value;
            double[][] matrixRight = ((Matrix) other).getValue();
            double[][] result = new double[matrixLeft.length][matrixRight[0].length];
            for (int i = 0; i < matrixLeft.length; i++) {
                for (int j = 0; j < matrixRight[0].length; j++) {
                    for (int k = 0; k < matrixRight.length; k++) {
                        result[i][j] = result[i][j] + matrixLeft[i][k] * matrixRight[k][j];
                    }
                }
            }
            return new Matrix(result);
        }
        return super.mul(other);
    }

    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder("{");
        String delimiter= "";
        for (int i = 0; i < value.length; i++) {
            sb.append("{");
            for (int j = 0; j < value[i].length; j++) {
                sb.append(delimiter);
                sb.append(value[i][j]);
                delimiter=", ";
                if ((i==value.length-1)&&j==value[i].length-1) delimiter="";
            }
            if (i<value.length-1) sb.append("}, ");
            else sb.append("}");
            delimiter="";
        }
        sb.append("}");
        return sb.toString();

    }
}
