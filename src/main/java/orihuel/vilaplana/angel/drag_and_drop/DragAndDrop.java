package orihuel.vilaplana.angel.drag_and_drop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DragAndDrop extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DragAndDrop.class.getResource("DragAndDropScene.fxml"));
        primaryStage.setTitle("Drag & Drop");
        Parent rootView = loader.load();
        Scene scene = new Scene(rootView);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
