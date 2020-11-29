package orihuel.vilaplana.angel.css;

import javafx.fxml.FXML;

import java.io.IOException;

public class LoginController {

    private CSSApp app;

    @FXML
    void handleIniciarSesion() {

    }

    @FXML
    void handleRegistrarse() throws IOException {
        app.setScene(2, "Registration Form", "RegisterView.fxml");
    }

    public void setApp(CSSApp app) {
        this.app = app;
    }

}
