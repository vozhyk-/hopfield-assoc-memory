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
                result.set(i, j, getCell(pane, i, j)));
        return result;
    }

    private double getCell(GridPane pane, int i, int j) {
        return Double.parseDouble(getPaneCell(pane, i, j).getText());
    }

    private TextField getPaneCell(GridPane pane, int i, int j) {
        return (TextField)pane.getChildren().get(i * width + j);
    }
}
