package hopfield_assoc_memory;

import hopfield_assoc_memory.algorithm.Matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MatrixFileReader {
    public static Matrix load(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            int height = scanner.nextInt();
            int width = scanner.nextInt();
            Matrix result = new Matrix(height, width);
            result.forEach((i, j, oldValue) ->
                    result.set(i, j, scanner.nextDouble()));
            return result;
        }
    }
}
