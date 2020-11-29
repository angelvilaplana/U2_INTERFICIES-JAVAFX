package orihuel.vilaplana.angel.css;

import javafx.fxml.FXML;

public class RegisterController {

    private CSSApp app;

    @FXML
    void handleStyle() {
        if (app.getStyles().equals("styles.css")) {
            app.setStyles("darkstyles.css");
        } else {
            app.setStyles("styles.css");
        }
    }

    public void setApp(CSSApp app) {
        this.app = app;
    }

}
