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


    /**
     * Inicialitzem el controlador
     */
    @FXML
    private void initialize() {
        // Fem que els TextFields soles puguen introduir decimals
        txtFieldDecimals(txtFieldOper1);
        txtFieldDecimals(txtFieldOper2);

        // Listener per als nostres RadioButtons per a veure quines operacions necesiten
        // un únic paràmetre
        operations.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            RadioButton operation = (RadioButton) newValue.getToggleGroup().getSelectedToggle();

            // Posem el nom de la operació en el Label principal
            labelHead.setText(operation.getText());

            // Ocultem els paràmetres si no cumpleixen alguna
            // de les condicions
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

            // Posem el resultat en blanc per a no confondrens
            txtFieldResult.setText("");
        });
    }

    /**
     * Acció quan clickem el botó de "=" per a que ens mostre el resultat
     */
    @FXML
    void handleResult() {
        // Obtenim el valor del primer TextField sempre y quan no estiga buit
        // y estiga disponible
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

        // Obtenim el valor del segon TextField sempre y quan no estiga buit
        // y estiga disponible
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

        // Obtenim la operació seleccionada
        RadioButton operation = (RadioButton) operations.getSelectedToggle();

        // Mirem quina operació esta seleccionada y obtenim el resultat
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

        // Condició per a verificar si el resultat es finit o infinit.
        // Si es infinit ens apareixera un missatge d'error
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

    /**
     * Métode per a mostrar la finestra de error
     * @param header Text principal del error
     * @param message Missatge del error
     */
    private void showErrorWindow(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();
    }

}
