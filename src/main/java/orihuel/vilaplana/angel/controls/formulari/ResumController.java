package orihuel.vilaplana.angel.controls.formulari;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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

    public void initialize(FormulariData formulariData) {
        labelName.setText(formulariData.getName());
        labelSurname.setText(formulariData.getSurname());
        labelCommentary.setText(formulariData.getCommentary());
        labelSex.setText(formulariData.getSex());
        labelCity.setText(formulariData.getCity());
        labelOperatingSystem.setText(formulariData.getOperatingSystem());
        labelComputerHours.setText(String.valueOf(formulariData.getComputerHours()));
        labelFormDate.setText(String.valueOf(formulariData.getFormDate()));
    }

}
