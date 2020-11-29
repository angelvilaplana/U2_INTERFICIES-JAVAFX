package orihuel.vilaplana.angel.css;

import javafx.fxml.FXML;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class LoginController {

    private CSSApp app;

    @FXML
    void handleIniciarSesion() throws IOException {
        String fxml = ".." + File.separator + "controls" + File.separator + "formulari" + File.separator + "FormulariScene.fxml";
        app.setScene(3, "Formulari", fxml);
    }

    @FXML
    void handleRegistrarse() throws IOException {
        app.setScene(2, "Registration Form", "RegisterView.fxml");
    }

    public void setApp(CSSApp app) {
        this.app = app;
    }

}
