package orihuel.vilaplana.angel.controls.formulari;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormulariController {

    @FXML
    private TextField txtFieldName;

    @FXML
    private TextField txtFieldSurname;

    @FXML
    private TextArea txtFieldCommentary;

    @FXML
    private ToggleGroup sexGroup;

    @FXML
    private ChoiceBox<String> choiceCity;

    @FXML
    private ChoiceBox<String> choiceOperatingSystem;

    @FXML
    private Spinner<Integer> choiceHoursComputer;

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
     * Acció quan fem click al mostrar el resum
     * Aquest métode el que farà serà obrir una nova finestra
     * amb el resultat que s'ha introduït al formulari
     * @throws Exception Problemes al cargar la escena
     */
    @FXML
    private void handleResum() throws Exception {
        String name = txtFieldName.getText();
        String surname = txtFieldSurname.getText();
        String commentary = txtFieldCommentary.getText();
        String sex = ((RadioButton) sexGroup.getSelectedToggle()).getText();
        String city = choiceCity.getValue();
        String operatingSystem = choiceOperatingSystem.getValue();
        int computerHours = choiceHoursComputer.getValue();
        LocalDate formDate = datePickForm.getValue();
        FormulariData formulariData = new FormulariData(name, surname, commentary, sex, city, operatingSystem, computerHours, formDate);
        formulariApp.showSummary(formulariData);
    }

}
