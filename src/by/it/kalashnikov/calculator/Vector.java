package by.it.kalashnikov.calculator;


import java.util.Arrays;

class Vector extends Var {

    private double[] value;

    double[] getValue() {
        return value;
    }

    public Vector(double[] value) {
        this.value = Arrays.copyOf(value, value.length);
    }

    public Vector(Vector vector) {
        this(vector.value);
    }


    Vector(String strVector) {
        String[] strings = strVector
                .replace('{', ' ').
                        replace('}', ' ').
                        trim().
                        split(",\\s*");
        double[] array = new double[strings.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Double.parseDouble(strings[i]);
        }
        this.value = array;
    }


    @Override
    public Var add(Var other) throws CalcException {
        if (other instanceof Scalar) {
            double[] res = Arrays.copyOf(value, value.length);
            for (int i = 0; i < res.length; i++) {
                res[i] = res[i] + ((Scalar) other).getValue();
            }
            return new Vector(res);
        } else if (other instanceof Vector) {

            double[] res = Arrays.copyOf(value, value.length);
            for (int i = 0; i < res.length; i++) {
                res[i] = res[i] + ((Vector) other).value[i];
                if (((Vector) other).value[i] < res[i])
                    throw new CalcException("Сложение невозможно");
            }
            return new Vector(res);
        } else
            return super.add(other);
    }

    @Override
    public Var sub(Var other) throws CalcException {
        if (other instanceof Scalar) {
            double[] res = Arrays.copyOf(value, value.length);
            for (int i = 0; i < res.length; i++) {
                res[i] = res[i] - ((Scalar) other).getValue();
            }
            return new Vector(res);
        } else if (other instanceof Vector) {
            double[] res = Arrays.copyOf(value, value.length);
            for (int i = 0; i < res.length; i++) {
                res[i] = res[i] - ((Vector) other).value[i];
                if (((Vector) other).value[i] > res[i])
                    throw new CalcException("Вычитание невозможно");
            }
            return new Vector(res);
        } else
            return super.sub(other);
    }

    @Override
    public Var mul(Var other) throws CalcException {
        if (other instanceof Scalar) {
            double[] res = Arrays.copyOf(value, value.length);
            for (int i = 0; i < res.length; i++) {
                res[i] = res[i] * ((Scalar) other).getValue();

            }
            return new Vector(res);
        } else if (other instanceof Vector) {
            double[] res = Arrays.copyOf(value, value.length);
            double resS = 0;
            for (int i = 0; i < res.length; i++) {
                resS += res[i] * ((Vector) other).value[i];
            }
            return new Scalar(resS);
        } else
            return super.mul(other);
    }
//    @Override
//    public Var div(Var other) {
//        if (other instanceof Scalar) {
//            double[] res = Arrays.copyOf(this.value, this.value.length);
//            double scalar = ((Scalar) other).getValue();
//            for (int i = 0; i < vector.length; i++) {
//                vector[i] /= scalar;
//            }
//            return new Vector(vector);
//        }
//        else return super.div(other);
//    }

    @Override
    public Var div(Var other) throws CalcException {
        if (other instanceof Scalar) {
            double[] res = Arrays.copyOf(value, value.length);
            for (int i = 0; i < res.length; i++) {
                res[i] = res[i] / ((Scalar) other).getValue();
            }
            return new Vector(res);
        } else if (other instanceof Vector) {
            double[] res = Arrays.copyOf(value, value.length);
            for (int i = 0; i < res.length; i++) {
                res[i] = res[i] / ((Vector) other).value[i];
            }
        }
        return super.div(other);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        String delimiter = "";
        for (double element : value) {
            sb.append(delimiter).append(element);
            delimiter = ", ";
        }
        sb.append("}");
        return sb.toString();
    }
}
