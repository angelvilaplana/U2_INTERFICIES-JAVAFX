package orihuel.vilaplana.angel.controls.formulari;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Efectes incluits en el FXML:
 *
 *  - Button (Mostrar resum):
 *      - Efecte utilitzat: ColorAdjust
 *      - Motiu: Per a que el botó siga més blanc i no siga
 *               tan oscur
 *
 *  - imageOperativeSystem:
 *      - Efecte utilitzat: Reflection
 *      - Motiu: Per a que la zona dreta no quede tant buit
 *
 */
public class FormulariController {

    @FXML
    private Label labelErrName;

    @FXML
    private TextField txtFieldName;

    @FXML
    private Label labelErrSurname;

    @FXML
    private TextField txtFieldSurname;

    @FXML
    private Label labelErrComentary;

    @FXML
    private TextArea txtFieldCommentary;

    @FXML
    private Label labelErrSex;

    @FXML
    private ToggleGroup sexGroup;

    @FXML
    private Label labelErrCity;

    @FXML
    private ChoiceBox<String> choiceCity;

    @FXML
    private Label labelErrSO;

    @FXML
    private ChoiceBox<String> choiceOperatingSystem;

    @FXML
    private Label labelErrHours;

    @FXML
    private Spinner<Integer> choiceHoursComputer;

    @FXML
    private Label labelErrDate;

    @FXML
    private DatePicker datePickForm;

    @FXML
    private ImageView imageOperativeSystem;

    private FormulariApp formulariApp;

    /**
     * Métode per a detectar d'on iniciem l'aplicació per a poder detectar
     * les finestres quina es la pare i quina es la filla
     * @param formulariApp Aplicació principal
     */
    public void setMain(FormulariApp formulariApp) {
        this.formulariApp = formulariApp;
    }

    /**
     * Métode per a inicializar el nostre funcionament de
     * l'aplicació
     */
    @FXML
    private void initialize() {
        // Comprobar els errors que pot produir-se en el formulari
        checkErrorsForm();

        // Posem en invisible els erros
        labelErrName.setVisible(false);
        labelErrSurname.setVisible(false);
        labelErrComentary.setVisible(false);
        labelErrSex.setVisible(false);
        labelErrCity.setVisible(false);
        labelErrSO.setVisible(false);
        labelErrHours.setVisible(false);
        labelErrDate.setVisible(false);

        // Anyadim les ciutats per al ChoiceBox
        choiceCity.getItems().add("Selecciona una ciutat...");
        choiceCity.getItems().add("Alcoi");
        choiceCity.getItems().add("València");
        choiceCity.getItems().add("Alacant");
        choiceCity.getItems().add("Castelló de la Plana");
        choiceCity.getItems().add("Elx");
        choiceCity.getItems().add("Gandia");
        choiceCity.getItems().add("Madrid");
        choiceCity.getItems().add("Barcelona");
        choiceCity.getItems().add("Girona");
        choiceCity.getItems().add("Santander");
        choiceCity.getItems().add("Sevilla");
        choiceCity.getItems().add("Bilbao");
        choiceCity.getItems().add("Saragossa");
        choiceCity.getItems().add("Toledo");
        choiceCity.getSelectionModel().select(0);

        // Anyadim els Sistemes Operatius per al ChoiceBox
        choiceOperatingSystem.getItems().add("Selecciona un sistema operatiu...");
        choiceOperatingSystem.getItems().add("Apple");
        choiceOperatingSystem.getItems().add("Linux");
        choiceOperatingSystem.getItems().add("Windows");
        choiceOperatingSystem.getSelectionModel().select(0);

        // Anyadim les imatges per al sistema operatiu elegit
        Image appleImage = new Image(String.valueOf(FormulariController.class.getResource("images/apple.png")));
        Image linuxImage = new Image(String.valueOf(FormulariController.class.getResource("images/linux.png")));
        Image windowsImage = new Image(String.valueOf(FormulariController.class.getResource("images/windows.png")));
        // Depenent de la selecció apareixera una imatge o altra
        choiceOperatingSystem.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    switch (newValue) {
                        case "Apple":
                            imageOperativeSystem.setImage(appleImage);
                            break;
                        case "Linux":
                            imageOperativeSystem.setImage(linuxImage);
                            break;
                        case "Windows":
                            imageOperativeSystem.setImage(windowsImage);
                            break;
                        default:
                            imageOperativeSystem.setImage(null);
                    }
                }
        );

        // Per a que sols es puga introduir números en el Spinner per a elegir la hora
        choiceHoursComputer.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                choiceHoursComputer.getEditor().setText(oldValue);
            }
        });

        // Que no ens mostre els números de les setmanes
        datePickForm.setShowWeekNumbers(false);
        // Format de la data a introduïr
        String patternDate = "dd/MM/yyyy";
        datePickForm.setPromptText(patternDate);
        // Convertim la data del DatePicker al format del "patternDate"
        datePickForm.setConverter(new StringConverter<>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(patternDate);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }

    /**
     * Métode per a comprobar els errors en el formulari y avisar
     * al usuari mediant Labels
     */
    private void checkErrorsForm() {
        txtFieldName.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && txtFieldName.getText().isEmpty()) {
                labelErrName.setVisible(true);
            } else if (!txtFieldName.getText().isEmpty()) {
                labelErrName.setVisible(false);
            }
        });

        txtFieldSurname.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && txtFieldSurname.getText().isEmpty()) {
                labelErrSurname.setVisible(true);
            } else if (!txtFieldSurname.getText().isEmpty()) {
                labelErrSurname.setVisible(false);
            }
        });

        txtFieldCommentary.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && txtFieldCommentary.getText().isEmpty()) {
                labelErrComentary.setVisible(true);
            } else if (!txtFieldCommentary.getText().isEmpty()) {
                labelErrComentary.setVisible(false);
            }
        });

        sexGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> labelErrSex.setVisible(false));

        choiceCity.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && choiceCity.getSelectionModel().getSelectedIndex() == 0) {
                labelErrCity.setVisible(true);
            } else if (choiceCity.getSelectionModel().getSelectedIndex() != 0) {
                labelErrCity.setVisible(false);
            }
        });

        choiceOperatingSystem.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && choiceOperatingSystem.getSelectionModel().getSelectedIndex() == 0) {
                labelErrSO.setVisible(true);
            } else if (choiceOperatingSystem.getSelectionModel().getSelectedIndex() != 0) {
                labelErrSO.setVisible(false);
            }
        });

        choiceHoursComputer.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && choiceHoursComputer.getValue() == null) {
                labelErrHours.setVisible(true);
            } else if (choiceHoursComputer.getValue() != null) {
                labelErrHours.setVisible(false);
            }
        });

        datePickForm.focusedProperty().addListener((observable, oldValue, newValue) -> {
            String dateText = datePickForm.getEditor().getText();
            if (!dateText.isEmpty()) {
                try {
                    datePickForm.setValue(LocalDate.parse(dateText, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    labelErrDate.setVisible(false);
                } catch (DateTimeParseException e) {
                    labelErrDate.setText("Format introduït no vàlid");
                    labelErrDate.setVisible(true);
                }
            } else if (!newValue && datePickForm.getValue() == null) {
                labelErrDate.setText("Tens que indicar la data!");
                labelErrDate.setVisible(true);
            } else if (datePickForm.getValue() != null) {
                labelErrDate.setVisible(false);
            }
        });
    }

    /**
     * Métode per a obtindre la informació del formulari
     * Comprobem també si tots els camps estàn complets.
     * Si no estàn farem visibles els labels de error i els farem
     * invisibles si estàn completats.
     * @return (0) Si tots els camps estàn complets retornara la informació
     *         del formulari. (1) Si no, tornara null
     */
    private FormulariData getFormulariData() {
        boolean isValidData = true;

        String name = txtFieldName.getText();
        if (name.isEmpty()) {
            labelErrName.setVisible(true);
            isValidData = false;
        } else {
            labelErrName.setVisible(false);
        }

        String surname = txtFieldSurname.getText();
        if (surname.isEmpty()) {
            labelErrSurname.setVisible(true);
            isValidData = false;
        } else {
            labelErrSurname.setVisible(false);
        }

        String commentary = txtFieldCommentary.getText();
        if (commentary.isEmpty()) {
            labelErrComentary.setVisible(true);
            isValidData = false;
        } else {
            labelErrComentary.setVisible(false);
        }

        String sex = null;
        if (sexGroup.getSelectedToggle() == null) {
            labelErrSex.setVisible(true);
            isValidData = false;
        } else {
            sex = ((RadioButton) sexGroup.getSelectedToggle()).getText();
            labelErrSex.setVisible(false);
        }

        String city = choiceCity.getValue();
        if (choiceCity.getSelectionModel().getSelectedIndex() == 0) {
            labelErrCity.setVisible(true);
            isValidData = false;
        } else {
            labelErrCity.setVisible(false);
        }

        String operatingSystem = choiceOperatingSystem.getValue();
        if (choiceOperatingSystem.getSelectionModel().getSelectedIndex() == 0) {
            labelErrSO.setVisible(true);
            isValidData = false;
        } else {
            labelErrSO.setVisible(false);
        }

        int computerHours = -1;
        if (choiceHoursComputer.getValue() == null) {
            labelErrHours.setVisible(true);
            isValidData = false;
        } else {
            computerHours = choiceHoursComputer.getValue();
            labelErrHours.setVisible(false);
        }

        LocalDate formDate = null;
        if (datePickForm.getValue() == null) {
            labelErrDate.setVisible(true);
            isValidData = false;
        } else {
            formDate = datePickForm.getValue();
            labelErrDate.setVisible(false);
        }

        if (isValidData) {
            return new FormulariData(name, surname, commentary, sex, city, operatingSystem, computerHours, formDate);
        } else {
            return null;
        }
    }

    /**
     * Acció quan fem click al mostrar el resum
     * Aquest métode el que farà serà obrir una nova finestra
     * amb el resultat que s'ha introduït al formulari sempre i
     * quan estiguen tots els camps complets
     * @throws Exception Problemes al cargar la escena
     */
    @FXML
    private void handleResum() throws Exception {
        FormulariData formulariData = getFormulariData();
        if (formulariData != null) {
            formulariApp.showSummary(formulariData);
        }
    }

}
