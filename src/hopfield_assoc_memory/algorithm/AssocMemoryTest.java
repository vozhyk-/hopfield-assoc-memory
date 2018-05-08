package hopfield_assoc_memory.algorithm;

import org.junit.jupiter.api.Test;

class AssocMemoryTest {
    // Based on https://www.youtube.com/watch?v=HoWJzeAT9uc
    @Test
    void testProducesCorrectWeights() {
        Vector[] to_remember = {
                new Vector(new double[]{1, -1, 1, -1, 1, -1, 1, -1}),
                new Vector(new double[]{1, 1, 1, 1, -1, -1, -1, -1})
        };
        AssocMemory m = new AssocMemory(new Matrix(to_remember));

        Matrix expectedWeights = new Matrix(new Vector[]{
                new Vector(new double[]{0, 0, 1, 0, 0, -1, 0, -1}),
                new Vector(new double[]{0, 0, 0, 1, -1, 0, -1, 0}),
                new Vector(new double[]{1, 0, 0, 0, 0, -1, 0, -1}),
                new Vector(new double[]{0, 1, 0, 0, -1, 0, -1, 0}),
                new Vector(new double[]{0, -1, 0, -1, 0, 0, 1, 0}),
                new Vector(new double[]{-1, 0, -1, 0, 0, 0, 0, 1}),
                new Vector(new double[]{0, -1, 0, -1, 1, 0, 0, 0}),
                new Vector(new double[]{-1, 0, -1, 0, 0, 1, 0, 0}),
        }).mult((double)1 / 4);

        assert m.weights.equals(expectedWeights);
    }

    // Based on https://www.youtube.com/watch?v=HoWJzeAT9uc
    @Test
    void testRemembersOriginalInputs() {
        Vector[] to_remember = {
                new Vector(new double[]{1, -1, 1, -1, 1, -1, 1, -1}),
                new Vector(new double[]{1, 1, 1, 1, -1, -1, -1, -1})
        };
        AssocMemory m = new AssocMemory(new Matrix(to_remember));

        assert m.apply(to_remember[0]).equals(to_remember[0]);
        assert m.apply(to_remember[1]).equals(to_remember[1]);
    }
}
