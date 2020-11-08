package orihuel.vilaplana.angel;

import javafx.fxml.FXML;
import orihuel.vilaplana.angel.animacions.Animacio;
import orihuel.vilaplana.angel.contenidors.ContenidorsApp;
import orihuel.vilaplana.angel.controls.ControlsApp;
import orihuel.vilaplana.angel.escacs.Escacs;
import orihuel.vilaplana.angel.listeners.EscacsListeners;
import orihuel.vilaplana.angel.piramide_de_cercles.PiramideCercles;

public class MainController {

    private MainApp mainApp;

    public void setMain(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleAnimacions() throws Exception {
        mainApp.displayApplication(new Animacio());
    }

    @FXML
    private void handleContenidors() throws Exception {
        mainApp.displayApplicationSameWindow(new ContenidorsApp());
    }

    @FXML
    private void handleControls() throws Exception {
        mainApp.displayApplicationSameWindow(new ControlsApp());
    }

    @FXML
    private void handleEscacs() throws Exception {
        mainApp.displayApplication(new Escacs());
    }

    @FXML
    private void handleListeners() throws Exception {
        mainApp.displayApplication(new EscacsListeners());
    }

    @FXML
    private void handlePiramide() throws Exception {
        mainApp.displayApplication(new PiramideCercles());
    }

}
