package by.it.shkatula.calculator;

import java.util.Arrays;

class Vector extends Var {
    private double[] value;

    Vector(double[] value) {
        this.value = value;
    }

    Vector(Vector vector) {
        this.value = setVector(vector.value);
    }

    Vector(String strVector) {
        this.value = parseString(strVector);

    }

    private double[] setVector(double[] vec) {
        double[] resultVector = new double[vec.length];
        for (int i = 0; i < resultVector.length; i++) {
            resultVector[i] = vec[i];
        }

        return resultVector;
    }


    //  {1.0, 2.0, 4.0}
    private double[] parseString(String line) {

        line = line.replaceAll("\\{", "");
        line = line.replaceAll("}", "");
        String[] qwe = line.split(", ?");
        double[] result = new double[qwe.length];
        for (int i = 0; i < qwe.length; i++) {
            result[i] = Double.parseDouble(qwe[i].trim());
        }
        return result;
    }

    public double[] getValue() {
        return value;
    }

    public void setValue(double[] value) {
        this.value = value;
    }

    @Override
    public Var add(Var other) throws CalcException{
        if (other instanceof Scalar) {
            double[] resultVecSca = Arrays.copyOf(value, value.length);
            for (int i = 0; i < resultVecSca.length; i++) {
                resultVecSca[i] = resultVecSca[i] + ((Scalar) other).getValue();
            }
            return new Vector(resultVecSca);
        } else if (other instanceof Vector) {
            double[] resultVecVec = Arrays.copyOf(value, value.length);
            for (int i = 0; i < resultVecVec.length; i++) {
                resultVecVec[i] = resultVecVec[i] + ((Vector) other).value[i];
            }
            return new Vector(resultVecVec);
        } else
            return super.add(other);
    }

    @Override
    public Var sub(Var other) throws CalcException{
        if (other instanceof Scalar) {
            double[] resultVecSca = Arrays.copyOf(value, value.length);
            for (int i = 0; i < resultVecSca.length; i++) {
                resultVecSca[i] = resultVecSca[i] - ((Scalar) other).getValue();
            }
            return new Vector(resultVecSca);
        } else if (other instanceof Vector) {
            double[] resultVecVec = Arrays.copyOf(value, value.length);
            for (int i = 0; i < resultVecVec.length; i++) {
                resultVecVec[i] = resultVecVec[i] - ((Vector) other).value[i];
            }
            return new Vector(resultVecVec);
        } else
            return super.sub(other);
    }

    @Override
    public Var mul(Var other) throws CalcException{
        if (other instanceof Scalar) {
            double[] resultVecSca = Arrays.copyOf(value, value.length);
            for (int i = 0; i < resultVecSca.length; i++) {
                resultVecSca[i] = resultVecSca[i] * ((Scalar) other).getValue();
            }
            return new Vector(resultVecSca);

        } else if (other instanceof Vector) {
            double[] resultVecVec = Arrays.copyOf(value, value.length);
            double sum = 0;
            for (int i = 0; i < resultVecVec.length; i++) {
                resultVecVec[i] = resultVecVec[i] * ((Vector) other).value[i];
            }
            for (double v : resultVecVec) {
                sum = sum + v;

            }
            return new Scalar(sum);
        } else
            return super.mul(other);
    }

    @Override
    public Var div(Var other) throws CalcException{
        if (other instanceof Scalar) {
            double[] resultVecSca = Arrays.copyOf(value, value.length);
            for (int i = 0; i < resultVecSca.length; i++) {
                resultVecSca[i] = resultVecSca[i] / ((Scalar) other).getValue();
            }
            return new Vector(resultVecSca);
        } else
            return super.mul(other);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        String delimeter = "";
        for (double element : value) {
            sb.append(delimeter).append(element);
            delimeter = ", ";
        }
        sb.append("}");
        return sb.toString();
    }
}
