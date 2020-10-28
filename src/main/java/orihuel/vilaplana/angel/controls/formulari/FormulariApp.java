package orihuel.vilaplana.angel.controls.formulari;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FormulariApp extends Application {

    // Escena principal
    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FormulariApp.class.getResource("FormulariScene.fxml"));
        Parent rootView = loader.load();
        FormulariController formulariController = loader.getController();
        formulariController.setMain(this);
        Scene scene = new Scene(rootView);
        primaryStage.setTitle("Exercici 2 - Formulari");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Métode que ens mostra el resum del formulari en una nova finestra
     * @param formulariData Objecte de les dades del formulari
     * @throws Exception Si ocurreix algun error al cargar el FXML
     */
    public void showSummary(FormulariData formulariData) throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FormulariApp.class.getResource("ResumScene.fxml"));
        Parent rootView = loader.load();
        Scene scene = new Scene(rootView);
        stage.setTitle("Resum Formulari");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        stage.setScene(scene);
        stage.setResizable(false);
        ResumController controller = loader.getController();
        controller.initialize(formulariData);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
