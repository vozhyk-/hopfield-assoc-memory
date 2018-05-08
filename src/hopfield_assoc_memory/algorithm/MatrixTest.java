package hopfield_assoc_memory.algorithm;

import org.junit.jupiter.api.Test;

class MatrixTest {
    @Test
    void multByConstant() {
        Matrix input = new Matrix(new Vector[]{
                new Vector(new double[]{0, -2}),
                new Vector(new double[]{2, 0})
        });
        Matrix expected = new Matrix(new Vector[]{
                new Vector(new double[]{0, -4}),
                new Vector(new double[]{4, 0})
        });
        assert input.mult(2).equals(expected);
    }

    @Test
    void multByFractionalConstant() {
        Matrix input = new Matrix(new Vector[]{
                new Vector(new double[]{0, -2}),
                new Vector(new double[]{2, 0})
        });
        Matrix expected = new Matrix(new Vector[]{
                new Vector(new double[]{0, -1}),
                new Vector(new double[]{1, 0})
        });
        assert input.mult((double)1 / 2).equals(expected);
    }
}