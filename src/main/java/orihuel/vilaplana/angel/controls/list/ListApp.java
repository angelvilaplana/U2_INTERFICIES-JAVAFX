package orihuel.vilaplana.angel.controls.list;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ListApp extends Application {

    private Stage primaryStage;

    /**
     * Métode per a iniciar la aplicació
     * principal
     *
     * @param stage Finestra que iniciem
     * @throws IOException Excepció si el loader no carga
     */
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ListApp.class.getResource("ListScene.fxml"));
        Parent rootView = loader.load();
        ListController listController = loader.getController();
        listController.setMain(this);
        Scene scene = new Scene(rootView);
        primaryStage.setTitle("Ejercici 3 - List");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Métode per a mostrar els elements seleccionats en altra
     * finestra
     *
     * @param selectedItems Els elements seleccionats en la llista
     *                      principal
     */
    public void showListSelectElements(ObservableList<String> selectedItems) {
        Stage stage = new Stage();
        stage.setTitle("Elementos Seleccionados");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);

        ListView<String> listView = new ListView<>();
        listView.setItems(selectedItems);

        Scene scene = new Scene(listView, 400,400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
