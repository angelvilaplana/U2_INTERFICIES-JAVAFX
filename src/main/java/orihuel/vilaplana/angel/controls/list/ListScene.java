package orihuel.vilaplana.angel.controls.list;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ListScene extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ListScene.class.getResource("ListScene.fxml"));
        Parent rootView = loader.load();
        Scene scene = new Scene(rootView);
        stage.setTitle("Ejercici 3 - List");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
