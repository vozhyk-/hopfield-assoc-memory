package hopfield_assoc_memory.algorithm;

import java.util.Arrays;
import java.util.function.Function;

public class Vector {
    private final double[] data;

    public Vector(double[] values) {
        this.data = values;
    }

    public Vector(int length) {
        this(new double[length]);
    }

    public int getLength() {
        return data.length;
    }

    public double get(int index) {
        return data[index];
    }

    public void set(int index, double value) {
        data[index] = value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vector))
            return false;

        Vector other = (Vector)o;
        return Arrays.equals(data, other.data);
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }

    public Vector map(Function<Double, Double> function) {
        Vector result = new Vector(getLength());
        forEach((i, value) -> result.set(i, function.apply(value)));
        return result;
    }

    public Matrix asMatrix() {
        Matrix result = new Matrix(getLength(), 1);
        for (int i = 0; i < getLength(); i++)
            result.set(i, 0, get(i));
        return result;
    }

    public void forEach(ForEachFunction function) {
        for (int i = 0; i < getLength(); i++)
            function.process(i, get(i));
    }

    @FunctionalInterface
    public interface ForEachFunction {
        void process(int i, double value);
    }
}
