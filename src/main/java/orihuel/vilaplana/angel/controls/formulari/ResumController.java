package orihuel.vilaplana.angel.controls.formulari;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.format.DateTimeFormatter;

public class ResumController {

    @FXML
    private Label labelName;

    @FXML
    private Label labelSurname;

    @FXML
    private Label labelCommentary;

    @FXML
    private Label labelSex;

    @FXML
    private Label labelCity;

    @FXML
    private Label labelOperatingSystem;

    @FXML
    private Label labelComputerHours;

    @FXML
    private Label labelFormDate;

    /**
     * El nostre propi inicializador de la escena on cargara totes les dades
     * del formulari anterior a esta escena
     * @param formulariData Objecte de les dades del formulari
     */
    public void initialize(FormulariData formulariData) {
        labelName.setText(formulariData.getName());
        labelSurname.setText(formulariData.getSurname());
        labelCommentary.setText(formulariData.getCommentary());
        labelSex.setText(formulariData.getSex());
        labelCity.setText(formulariData.getCity());
        labelOperatingSystem.setText(formulariData.getOperatingSystem());
        labelComputerHours.setText(String.valueOf(formulariData.getComputerHours()));
        labelFormDate.setText(formulariData.getFormDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

}
