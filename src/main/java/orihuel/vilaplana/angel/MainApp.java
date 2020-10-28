package orihuel.vilaplana.angel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("MainView.fxml"));
        Parent rootView = loader.load();
        MainController mainController = loader.getController();
        mainController.setMain(this);
        Scene scene = new Scene(rootView);
        primaryStage.setTitle("Ut2. Generació d'interficies d'escriptori");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void displayApplicationSameWindow(Application application) throws Exception {
        application.start(primaryStage);
        primaryStage.show();
    }

    public void displayApplication(Application application) throws Exception {
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        application.start(stage);
        stage.show();
    }

}
