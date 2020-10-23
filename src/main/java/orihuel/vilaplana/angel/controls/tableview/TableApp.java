package orihuel.vilaplana.angel.controls.tableview;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TableApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TableApp.class.getResource("TableScene.fxml"));
        Parent rootView = loader.load();
        Scene scene = new Scene(rootView);
        stage.setTitle("Exercici 4 - TableView");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        TableApp.launch();
    }

}
