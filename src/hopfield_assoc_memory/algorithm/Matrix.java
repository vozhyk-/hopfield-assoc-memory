package hopfield_assoc_memory.algorithm;

import java.util.Arrays;

public class Matrix {
    private final double[] data;

    private final int height;
    private final int width;

    public Matrix(int height, int width) {
        this.height = height;
        this.width = width;
        this.data = new double[height * width];
    }

    public Matrix(Vector[] columns) {
        if (columns.length < 1)
            throw new IllegalArgumentException();

        this.width = columns.length;
        this.height = columns[0].getLength();
        this.data = new double[height * width];

        for (int j = 0; j < columns.length; j++) {
            Vector column = columns[j];
            int columnHeight = column.getLength();
            if (columnHeight != this.height)
                throw new IllegalArgumentException();

            for (int i = 0; i < columnHeight; i++)
                set(i, j, column.get(i));
        }
    }

    private double get(int row, int column) {
        return data[row * width + column];
    }

    public void set(int row, int column, double value) {
        data[row * width + column] = value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Matrix))
            return false;

        Matrix other = (Matrix)o;
        return width == other.width && height == other.height &&
                Arrays.equals(data, other.data);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                builder.append(get(i, j));
                builder.append(", ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public static Matrix identityMatrix(int size) {
        Matrix result = new Matrix(size, size);
        for (int i = 0; i < size; i++)
            result.set(i, i, 1);
        return result;
    }

    public Matrix transpose() {
        // width and height swapped
        @SuppressWarnings("SuspiciousNameCombination")
        Matrix result = new Matrix(width, height);

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                result.set(j, i, get(i, j));

        return result;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Matrix mult(Matrix other) {
        if (width != other.height)
            throw new IllegalArgumentException();

        Matrix result = new Matrix(height, other.width);

        for (int i = 0; i < result.height; i++) {
            for (int j = 0; j < result.width; j++) {
                double sum = 0;
                for (int k = 0; k < width; k++)
                    sum += get(i, k) * other.get(k, j);
                result.set(i, j, sum);
            }
        }

        return result;
    }

    public Matrix mult(double constant) {
        Matrix result = new Matrix(height, width);
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                result.set(i, j, constant * get(i, j));
        return result;
    }

    public Matrix add(Matrix other) {
        if (height != other.height ||
                width != other.width)
            throw new IllegalArgumentException();

        Matrix result = new Matrix(height, width);
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                result.set(i, j, get(i, j) + other.get(i, j));
        return result;
    }

    public Vector mult(Vector vector) {
        return mult(vector.asMatrix()).asVector();
    }

    public void forEach(ForEachFunction function) {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                function.process(i, j, get(i, j));
    }

    public Vector asVector() {
        if (width != 1)
            throw new IllegalArgumentException();

        Vector result = new Vector(height);
        for (int i = 0; i < height; i++)
            result.set(i, get(i, 0));
        return result;
    }

    @FunctionalInterface
    public interface ForEachFunction {
        void process(int i, int j, double value);
    }
}
