package hopfield_assoc_memory;

import hopfield_assoc_memory.algorithm.AssocMemory;
import hopfield_assoc_memory.algorithm.Matrix;
import hopfield_assoc_memory.algorithm.Vector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Controller {
    @FXML
    TextField heightField;
    @FXML
    TextField widthField;

    @FXML
    GridPane toRememberPane;

    @FXML
    GridPane weightsPane;

    @FXML
    GridPane testVectorPane;

    @FXML
    GridPane outputVectorPane;

    private int height;
    private int width;
    private AssocMemory memory;

    public void setSize(ActionEvent event) {
        height = Integer.parseInt(heightField.getText());
        width = Integer.parseInt(widthField.getText());

        Matrix toRemember = new Matrix(height, width);
        putMatrix(toRemember, toRememberPane);
    }

    public void computeMemory(ActionEvent event) {
        Matrix toRemember = getMatrix(toRememberPane);
        memory = new AssocMemory(toRemember);
        putMatrix(memory.getWeights(), weightsPane);

        Vector testVector = new Vector(height);
        putMatrix(testVector.asMatrix(), testVectorPane);
    }

    public void testVector(ActionEvent event) {
        Vector inputVector = getVector(testVectorPane);
        Vector outputVector = memory.apply(inputVector);
        putMatrix(outputVector.asMatrix(), outputVectorPane);
    }

    private void putMatrix(Matrix toRemember, GridPane grid) {
        grid.getChildren().clear();
        toRemember.forEach((i, j, value) -> {
            TextField field = new TextField(
                    ((Double)value).toString());
            grid.add(field, j, i);
        });
    }

    private Matrix getMatrix(GridPane pane) {
        Matrix result = new Matrix(height, width);
        result.forEach((i, j, oldValue) ->
                result.set(i, j, getMatrixCell(pane, i, j)));
        return result;
    }

    private Vector getVector(GridPane pane) {
        Vector result = new Vector(height);
        result.forEach((i, oldValue) -> result.set(i, getVectorCell(pane, i)));
        return result;
    }

    private double getVectorCell(GridPane pane, int i) {
        return Double.parseDouble(getCellField(pane, 1, i, 0).getText());
    }

    private double getMatrixCell(GridPane pane, int i, int j) {
        return Double.parseDouble(getCellField(pane, width, i, j).getText());
    }

    private TextField getCellField(GridPane pane, int width, int i, int j) {
        return (TextField)pane.getChildren().get(i * width + j);

    }
}
