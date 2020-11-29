package orihuel.vilaplana.angel.css;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RegisterController {

    @FXML
    private Button btnMode;

    private CSSApp app;

    @FXML
    public void initialize(CSSApp app) {
        this.app = app;

        if (app.getStyles().equals("styles.css")) {
            btnMode.setText("Modo nit");
        } else {
            btnMode.setText("Modo normal");
        }
    }

    @FXML
    private void handleStyle() {
        if (app.getStyles().equals("styles.css")) {
            app.setStyles("darkstyles.css");
            btnMode.setText("Modo normal");
        } else {
            app.setStyles("styles.css");
            btnMode.setText("Modo nit");
        }
    }

}
