package orihuel.vilaplana.angel.css;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;

public class LoginController {

    @FXML
    private PasswordField tfPass;

    @FXML
    private TextField tfUserName;

    private CSSApp app;

    private String userName;

    private String password;

    @FXML
    public void initialize() {
        userName = "angel";
        password = "1234";
    }

    @FXML
    private void handleIniciarSesion() throws IOException {
        if (tfUserName.getText().equals(userName) && tfPass.getText().equals(password)) {
            String fxml = ".." + File.separator + "controls" + File.separator + "formulari" + File.separator + "FormulariScene.fxml";
            app.setScene(3, "Formulari", fxml);
        } else {
            tfUserName.setText("");
            tfPass.setText("");
        }
    }

    @FXML
    private void handleRegistrarse() throws IOException {
        app.setScene(2, "Registration Form", "RegisterView.fxml");
    }

    public void setApp(CSSApp app) {
        this.app = app;
    }

}
