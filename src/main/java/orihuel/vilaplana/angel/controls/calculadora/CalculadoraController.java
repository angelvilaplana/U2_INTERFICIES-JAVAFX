package orihuel.vilaplana.angel.controls.calculadora;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CalculadoraController {

    @FXML
    private Label labelHead;

    @FXML
    private TextField txtFieldOper1;

    @FXML
    private TextField txtFieldOper2;

    @FXML
    private TextField txtFieldResult;

    @FXML
    private ToggleGroup operations;

    @FXML
    private RadioButton radioSum;

    @FXML
    private RadioButton radioRest;

    @FXML
    private RadioButton radioMult;

    @FXML
    private RadioButton radioDiv;

    @FXML
    private RadioButton radioElev2;

    @FXML
    private RadioButton radioElev3;

    @FXML
    private RadioButton radioSqrt;

    @FXML
    private RadioButton radioSin;

    @FXML
    private RadioButton radioCos;

    @FXML
    private RadioButton radioTan;

    @FXML
    private RadioButton radioLog10;

    @FXML
    private RadioButton radioLogNep;

    @FXML
    private RadioButton radioExpNat;


    @FXML
    private void initialize() {
        txtFieldDecimals(txtFieldOper1);
        txtFieldDecimals(txtFieldOper2);

        operations.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            RadioButton operation = (RadioButton) newValue.getToggleGroup().getSelectedToggle();
            labelHead.setText(operation.getText());

            txtFieldOper2.setManaged(
                       !operation.equals(radioElev2)
                    && !operation.equals(radioElev3)
                    && !operation.equals(radioSqrt)
                    && !operation.equals(radioSin)
                    && !operation.equals(radioCos)
                    && !operation.equals(radioTan)
                    && !operation.equals(radioLog10)
                    && !operation.equals(radioLogNep)
                    && !operation.equals(radioExpNat)
            );

            txtFieldResult.setText("");
        });
    }

    @FXML
    void handleResult() {
        RadioButton operation = (RadioButton) operations.getSelectedToggle();

        double oper1 = 0;
        if (txtFieldOper1.managedProperty().get()) {
            if (!txtFieldOper1.getText().isEmpty()) {
                oper1 = Double.parseDouble(txtFieldOper1.getText());
            } else {
                showErrorWindow("No se ha introducido ningún valor en el primer campo",
                               "Por favor, Introduzca un valor en el primer campo para " +
                                       "realizar la operación matemática");
                return;
            }
        }

        double oper2 = 0;
        if (txtFieldOper2.managedProperty().get()) {
            if (!txtFieldOper2.getText().isEmpty()) {
                oper2 = Double.parseDouble(txtFieldOper2.getText());
            } else {
                showErrorWindow("No se ha introducido ningún valor en el segundo campo",
                               "Por favor, Introduzca un valor en el segundo campo para " +
                                       "realizar la operación matemática");
                return;
            }
        }

        double result = 0;

        if (operation.equals(radioSum)) {
            result = oper1 + oper2;
        } else if (operation.equals(radioRest)) {
            result = oper1 - oper2;
        } else if (operation.equals(radioMult)) {
            result = oper1 * oper2;
        } else if (operation.equals(radioDiv)) {
            result = oper1 / oper2;
        } else if (operation.equals(radioElev2)) {
            result = oper1 * oper1;
        } else if (operation.equals(radioElev3)) {
            result = oper1 * oper1 * oper1;
        } else if (operation.equals(radioSqrt)) {
            result = Math.sqrt(oper1);
        } else if (operation.equals(radioSin)) {
            result = Math.sin(oper1);
        } else if (operation.equals(radioCos)) {
            result = Math.cos(oper1);
        } else if (operation.equals(radioTan)) {
            result = Math.tan(oper1);
        } else if (operation.equals(radioLog10)) {
            result = Math.log10(oper1);
        } else if (operation.equals(radioLogNep)) {
            result = Math.log(oper1);
        } else if (operation.equals(radioExpNat)) {
            result = Math.exp(oper1);
        }

        if (Double.isFinite(result)) {
            txtFieldResult.setText(String.valueOf(result));
        } else {
            showErrorWindow("Operación no válida", "El resultado da infinito");
        }
    }

    /**
     * Métode per a que el TextField soles puga introduir
     * números decimals
     * @param textField TextField que volem que tinga efecte
     */
    private void txtFieldDecimals(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Per si el usuari introduiex la ',' ho cambie per el '.'
            newValue = newValue.replace(',','.');
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                textField.setText(oldValue);
            } else {
                textField.setText(newValue);
            }
        });
    }

    private void showErrorWindow(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();
    }

}
