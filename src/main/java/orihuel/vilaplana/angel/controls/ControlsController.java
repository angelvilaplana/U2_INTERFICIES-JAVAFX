package orihuel.vilaplana.angel.controls;

import javafx.fxml.FXML;
import orihuel.vilaplana.angel.controls.calculadora.CalculadoraApp;
import orihuel.vilaplana.angel.controls.formulari.FormulariApp;
import orihuel.vilaplana.angel.controls.list.ListApp;
import orihuel.vilaplana.angel.controls.tableview.TableApp;

public class ControlsController {

    private ControlsApp controlsApp;

    public void setMain(ControlsApp controlsApp) {
        this.controlsApp = controlsApp;
    }

    @FXML
    void handleCalculadora() throws Exception {
        controlsApp.displayApplication(new CalculadoraApp());
    }

    @FXML
    void handleFormulari() throws Exception {
        controlsApp.displayApplication(new FormulariApp());
    }

    @FXML
    void handleList() throws Exception {
        controlsApp.displayApplication(new ListApp());
    }

    @FXML
    void handleTableView() throws Exception {
        controlsApp.displayApplication(new TableApp());
    }

    @FXML
    void handleReturnMenu() throws Exception {
        controlsApp.displayMainApp();
    }

}
