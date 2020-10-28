package orihuel.vilaplana.angel.controls;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import orihuel.vilaplana.angel.MainApp;

public class ControlsApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ControlsApp.class.getResource("ControlsView.fxml"));
        Parent rootView = loader.load();
        ControlsController controlsController = loader.getController();
        controlsController.setMain(this);
        Scene scene = new Scene(rootView);
        primaryStage.setTitle("Controls");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void displayApplication(Application application) throws Exception {
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        application.start(stage);
        stage.show();
    }

    public void displayMainApp() throws Exception {
        MainApp mainApp = new MainApp();
        mainApp.start(primaryStage);
        primaryStage.show();
    }

}
