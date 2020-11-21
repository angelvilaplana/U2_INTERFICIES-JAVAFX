package orihuel.vilaplana.angel.drag_and_drop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Optional;

public class DragAndDrop extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DragAndDrop.class.getResource("DragAndDropScene.fxml"));
        primaryStage.setTitle("Drag & Drop");
        Parent rootView = loader.load();
        DragAndDropController controller = loader.getController();
        controller.setMainApp(this);
        Scene scene = new Scene(rootView);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Dialeg y música al acabr el joc
     */
    public void endGame(int movements) {
        // Indiquem la música i ho reproduim
        Media media = new Media(DragAndDrop.class.getResource("the_avengers.mp3").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setStartTime(new Duration(0));
        mediaPlayer.play();

        // Dialeg indicant els moviments
        Image image = new Image((DragAndDrop.class.getResource("images/logo.png").toString()), 100, 100, true, true);
        ImageView imageView = new ImageView(image);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Joc finalitzat");
        dialog.setGraphic(imageView);
        ButtonType btnRestartGame = new ButtonType("Tornar a jugar");
        ButtonType btnExit = new ButtonType("Eixir");
        AnchorPane pane = new AnchorPane();
        Label label = new Label("Has necesitat " + movements + " moviments per a acabar");
        AnchorPane.setTopAnchor(label, (double) 0);
        AnchorPane.setBottomAnchor(label, (double) 0);
        AnchorPane.setLeftAnchor(label, (double) 20);
        AnchorPane.setRightAnchor(label, (double) 20);
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font("Arial", 16));
        label.setPrefWidth(100);
        label.setWrapText(true);
        pane.getChildren().add(label);
        dialog.getDialogPane().setContent(pane);
        dialog.getDialogPane().getButtonTypes().addAll(btnRestartGame, btnExit);

        // Comprobar quin botó ha pulsat l'usuari
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.get() == btnRestartGame) {
            try {
                start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (result.get() == btnExit) {
            primaryStage.close();
        }

        // Parem la música èpica
        mediaPlayer.stop();
    }

    public static void main(String[] args) {
        launch();
    }

}
