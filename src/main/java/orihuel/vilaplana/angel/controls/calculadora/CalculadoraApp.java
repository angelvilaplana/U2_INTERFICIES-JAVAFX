package orihuel.vilaplana.angel.controls.calculadora;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalculadoraApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CalculadoraApp.class.getResource("CalculadoraScene.fxml"));
        Parent rootView = loader.load();
        Scene scene = new Scene(rootView);
        stage.setTitle("Exercici 1 - Calculadora");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
