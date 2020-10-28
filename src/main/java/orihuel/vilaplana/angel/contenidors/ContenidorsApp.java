package orihuel.vilaplana.angel.contenidors;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import orihuel.vilaplana.angel.MainApp;

public class ContenidorsApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ContenidorsApp.class.getResource("ContenidorsView.fxml"));
        Parent rootView = loader.load();
        ContenidorsController contenidorsController = loader.getController();
        contenidorsController.setMain(this);
        Scene scene = new Scene(rootView);
        primaryStage.setTitle("Contenidors");
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
