package hopfield_assoc_memory.algorithm;

public class AssocMemory {
    Matrix weights;

    public AssocMemory(Matrix x) {
        int n_neurons = x.getHeight();
        int n_vectors = x.getWidth();

        this.weights = x.mult(x.transpose()).add(
                Matrix.identityMatrix(n_neurons).mult(-n_vectors)).mult(
                (double)1 / n_neurons);
    }

    public Vector apply(Vector input) {
        return polarize(weights.mult(input));
    }

    private Vector polarize(Vector input) {
        return input.map(AssocMemory::polarize);
    }

    private static double polarize(double v) {
        if (v > 0)
            return 1;
        else
            return -1;
    }

    public Matrix getWeights() {
        return weights;
    }
}
