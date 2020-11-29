package orihuel.vilaplana.angel.css;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.prefs.Preferences;

public class CSSApp extends Application {

    private String nameScene;

    private Preferences preferences;

    private Stage primaryStage;

    private Parent rootView;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        nameScene = "Login";
        primaryStage = stage;
        loader.setLocation(CSSApp.class.getResource("LoginView.fxml"));
        rootView = loader.load();
        preferences = Preferences.userNodeForPackage(this.getClass());
        String prefsStyles = preferences.get("styles", "styles.css");
        rootView.getStylesheets().set(0, CSSApp.class.getResource(prefsStyles).toString());
        LoginController controller = loader.getController();
        controller.setApp(this);
        Scene scene = new Scene(rootView);
        primaryStage.setTitle("Finestra 1 - " + nameScene);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            try {
                if (!nameScene.equals("Login")) {
                    CSSApp app = new CSSApp();
                    app.start(new Stage());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void setScene(int numFinestra, String nameScene, String fxml) throws IOException {
        this.nameScene = nameScene;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CSSApp.class.getResource(fxml));
        rootView = loader.load();
        rootView.getStylesheets().set(0, CSSApp.class.getResource(getStyles()).toString());
        Scene scene = new Scene(rootView);
        primaryStage.setTitle("Finestra " + numFinestra + " - "  + nameScene);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        if (nameScene.equals("Registration Form")) {
            RegisterController controller = loader.getController();
            controller.setApp(this);
        }

        primaryStage.show();
    }

    public void setStyles(String styles) {
        preferences.put("styles", styles);
        rootView.getStylesheets().set(0, CSSApp.class.getResource(styles).toString());
    }

    public String getStyles() {
        return preferences.get("styles", "styles.css");
    }

    public static void main(String[] args) {
        launch();
    }

}
