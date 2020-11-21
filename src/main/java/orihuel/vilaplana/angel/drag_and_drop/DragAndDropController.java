package orihuel.vilaplana.angel.drag_and_drop;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class DragAndDropController {

    @FXML
    private ImageView imageDrag1;

    @FXML
    private ImageView imageDrag2;

    @FXML
    private ImageView imageDrag3;

    @FXML
    private ImageView imageDrag4;

    @FXML
    private Label labelCard1;

    @FXML
    private Label labelCard2;

    @FXML
    private Label labelCard3;

    @FXML
    private Label labelCard4;

    @FXML
    private ImageView imageDown1;

    @FXML
    private ImageView imageDown2;

    @FXML
    private ImageView imageDown3;

    @FXML
    private ImageView imageDown4;

    @FXML
    private ImageView imageDrop1;

    @FXML
    private ImageView imageDrop2;

    @FXML
    private ImageView imageDrop3;

    @FXML
    private ImageView imageDrop4;

    private Stage mainStage;

    private List<Card> cards;

    private int movements;

    private int cardsCorrects;

    /**
     * Inicializem el joc
     */
    @FXML
    private void initialize() {
        initializeCards();
        initializeImagesDrag();
        initializeLabels();
        initializeImagesDrop();
        movements = 0;
        cardsCorrects = 0;
    }

    /**
     * Inicializem les cartes amb
     * les seues dades
     */
    private void initializeCards() {
        cards = new ArrayList<>();
        cards.add(new Card("Hulk", DragAndDrop.class.getResource("images/hulk.png")));
        cards.add(new Card("Ironman", DragAndDrop.class.getResource("images/ironman.png")));
        cards.add(new Card("Spiderman", DragAndDrop.class.getResource("images/spiderman.png")));
        cards.add(new Card("Thor", DragAndDrop.class.getResource("images/thor.png")));
    }

    /**
     * Inicializem les imatges principals per a que el puga'm arrossegar
     * El que es fa es crear una llista nova copiant els elements principals
     * y en aquesta llista anem triant aleatoriament y eliminant el que ja s'ha triat
     */
    private void initializeImagesDrag() {
        Random random = new Random();
        List<Card> randomImages = new ArrayList<>(cards);
        Card randomImage;

        randomImage = randomImages.get(random.nextInt(randomImages.size()));
        setImageDrag(imageDrag1, randomImage.getPathImage());
        getCard(randomImage.getPathImage()).setPosition(1,1);
        randomImages.remove(randomImage);

        randomImage = randomImages.get(random.nextInt(randomImages.size()));
        setImageDrag(imageDrag2, randomImage.getPathImage());
        getCard(randomImage.getPathImage()).setPosition(1,2);
        randomImages.remove(randomImage);

        randomImage = randomImages.get(random.nextInt(randomImages.size()));
        setImageDrag(imageDrag3, randomImage.getPathImage());
        getCard(randomImage.getPathImage()).setPosition(1,3);
        randomImages.remove(randomImage);

        randomImage = randomImages.get(0);
        setImageDrag(imageDrag4, randomImage.getPathImage());
        getCard(randomImage.getPathImage()).setPosition(1,4);
    }

    /**
     * Inicializem els Labels igual que em fet amb les imatges.
     * Fent que en cada nova partida siga diferent
     */
    private void initializeLabels() {
        Random random = new Random();
        List<Card> randomLabels = new ArrayList<>(cards);;
        Card randomLabel;

        randomLabel = randomLabels.get(random.nextInt(randomLabels.size()));
        labelCard1.setText(randomLabel.getName());
        randomLabels.remove(randomLabel);

        randomLabel = randomLabels.get(random.nextInt(randomLabels.size()));
        labelCard2.setText(randomLabel.getName());
        randomLabels.remove(randomLabel);

        randomLabel = randomLabels.get(random.nextInt(randomLabels.size()));
        labelCard3.setText(randomLabel.getName());
        randomLabels.remove(randomLabel);

        randomLabel = randomLabels.get(0);
        labelCard4.setText(randomLabel.getName());
    }

    /**
     * Inicializem les imatges del segon grid per a que
     * quan arroseguem les imatges de dalt
     * puguen soltarse
     */
    private void initializeImagesDrop() {
        handleDropImage(imageDrop1, 1);
        handleDropImage(imageDrop2, 2);
        handleDropImage(imageDrop3, 3);
        handleDropImage(imageDrop4, 4);
    }

    /**
     * Posem una imatge y afegim les funcions quan el usuari
     * arrossega la imatge
     *
     * @param imageDrag Imatge que s'arrossegara
     * @param pathImage Direcció on s'encontra la imatge
     */
    private void setImageDrag(ImageView imageDrag, String pathImage) {
        imageDrag.setImage(new Image(pathImage));

        // Quan el usuari està damunt de la imatge
        // Cambiem el cursor per el de la mà
        imageDrag.setOnMouseEntered(e -> {
            if (imageDrag.getImage() != null && imageDrag.getImage().getUrl() != null) {
                imageDrag.setCursor(Cursor.HAND);
            }
        });

        // Si l'usuari s'encontra arrossega la imatge el que farà serà que el cursor cambie
        // per el de la mà tancada + cridarem al métode "handleDragImage" per a que ens guarde
        // les dades principals de la carta
        imageDrag.setOnDragDetected(e -> {
            if (imageDrag.getImage() != null && imageDrag.getImage().getUrl() != null) {
                imageDrag.setCursor(Cursor.CLOSED_HAND);
                handleDragImage(imageDrag);
            }
        });

        // Es crida quan l'usuari acaba de arrossegar
        imageDrag.setOnDragDone(e -> {
            // Si la imatge s'ha arrosegat bé
            if (e.isAccepted()) {
                Dragboard dragboard = e.getDragboard();

                // Si en el lloc on s'ha posat la imatge
                // hi había una imatge
                if (dragboard.getUrl() != null) {
                    Card card = getCard(dragboard.getUrl());

                    // Si la carta esta en la segon Grid. Comprobem si on s'ha colocat
                    // automàticament es correcta. Si es correcta ho indiquem y afegim una carta correcta.
                    // Si no, que siga al contrari
                    if (card.getGrid() == 2 && isCorrectCard(card)) {
                        setImageCorrectDrag(imageDrag, card.getPathImage());
                        setImageDown(card.getColumn(), DragAndDrop.class.getResource("images/correct.png").toString());
                        cardsCorrects++;
                    } else {
                        setImageDrag(imageDrag, card.getPathImage());
                    }
                } else {
                    imageDrag.setImage(null);
                }

                // Posem que el cursor estiga per defecte
                imageDrag.setCursor(Cursor.DEFAULT);

                // Aumentem els moviments de l'usuari i
                // comprobem si l'usuari ha fet totes les
                // cartes correctament i executem
                // el métode "endGame"
                movements++;
                if (cardsCorrects == cards.size()) {
                    endGame();
                }
            }
        });
    }

    /**
     * Deshabilitem aquesta imatge per a que no es
     * puga arrossegar ja que es correcta
     *
     * @param imageDrag Imatge que no volem que s'arrossegue
     * @param pathImage Direcció de la imatge
     */
    private void setImageCorrectDrag(ImageView imageDrag, String pathImage) {
        imageDrag.setImage(new Image(pathImage));
        imageDrag.setOnMouseEntered(e -> imageDrag.setCursor(Cursor.DEFAULT));
        imageDrag.setOnDragDetected(e -> {});
        imageDrag.setOnDragDone(e -> {});
        imageDrag.setOnDragOver(e -> {});
        imageDrag.setOnDragDropped(e -> {});
    }

    /**
     * Métode per a que a gurdar les dades
     * de la imatge que arrossega l'usuari
     *
     * @param imageDrag Imatge que arrossega
     *                  i que guarda les dades
     */
    private void handleDragImage(ImageView imageDrag) {
        Dragboard dragboard = imageDrag.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        Image image = new Image(imageDrag.getImage().getUrl(), imageDrag.getFitWidth(), imageDrag.getFitHeight(), true, true);
        content.putImage(image);
        content.putUrl(image.getUrl());
        dragboard.setContent(content);
    }

    /**
     * Afegim les funcions per a les imatges que poden obtindre
     * una imatge que es arrossegada
     *
     * @param imageDrop Imatge que es pot soltar
     * @param column Columna on s'encontra aquesta imatge
     */
    private void handleDropImage(ImageView imageDrop, int column) {
        // Per a detectar si hi ha una imatge arrossegantse
        // per damunt d'ell
        imageDrop.setOnDragOver(e -> {
            Dragboard dragboard = e.getDragboard();
            if(dragboard.hasImage()){
                e.acceptTransferModes(TransferMode.MOVE);
            }
            e.consume();
        });

        // Per a detectar que una imatge s'ha soltat
        // damunt d'ell
        imageDrop.setOnDragDropped(e -> {
            Dragboard dragboard = e.getDragboard();

            // Si quan arrosseguem la imatge te dades
            if (dragboard.hasImage() && (imageDrop.getImage() == null || !imageDrop.getImage().getUrl().equals(dragboard.getUrl()))) {
                // Obtenim la carta i indiquem la posició
                // actual on s'ha arrossegat
                Card newCard = getCard(dragboard.getUrl());
                int[] positionDragBoard = {2, column};

                // Afegim noves dades per a que les obtinga quan la imatge
                // s'ha soltat amb èxit
                ClipboardContent content = new ClipboardContent();
                // Si la imatge on anem a soltar conté una imatge dins el que farem serà cambiar a la posició
                // de la carta que anem a posar. Si la carta anterior que em arrossegat ja estava
                // en la segona Grid farem que la imatge de baix que és incorrecte desaparega
                if (imageDrop.getImage() != null) {
                    Card card = getCard(imageDrop.getImage().getUrl());
                    positionDragBoard = card.getPosition();
                    card.setPosition(newCard.getPosition());
                    content.putUrl(card.getPathImage());
                } else if (newCard.getGrid() == 2) {
                    setImageDown(newCard.getColumn(), null);
                }

                // Guardem el nou contigut
                dragboard.setContent(content);

                // Coloquem la imatge en la nova posició
                newCard.setPosition(positionDragBoard);

                // Comprobem si la carta esta en la posició correcte.
                // Si es aixi posarem la imatge de correcte i farem que ja no es puga arrossegar.
                // Ademés li afegirem que una carta ha sigut correcta
                // Si es el contrari farem el contrari
                if (isCorrectCard(newCard)) {
                    setImageCorrectDrag(imageDrop, newCard.getPathImage());
                    setImageDown(newCard.getColumn(), DragAndDrop.class.getResource("images/correct.png").toString());
                    cardsCorrects++;
                } else {
                    setImageDrag(imageDrop, newCard.getPathImage());
                    setImageDown(newCard.getColumn(), DragAndDrop.class.getResource("images/incorrect.png").toString());
                }

                e.setDropCompleted(true);
            } else {
                e.setDropCompleted(false);
            }
            e.consume();
        });
    }

    /**
     * Dialeg y música al acabr el joc
     */
    private void endGame() {
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
        ButtonType btnReturnMenu = new ButtonType("Tornar al menú");
        ButtonType btnRestartGame = new ButtonType("Tornar a jugar");
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
        dialog.getDialogPane().getButtonTypes().addAll(btnReturnMenu, btnRestartGame);

        // Comprobar quin botó ha pulsat l'usuari
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.get() == btnReturnMenu) {
            mainStage.close();
        } else if (result.get() == btnRestartGame) {
            DragAndDrop dragAndDrop = new DragAndDrop();
            try {
                dragAndDrop.start(mainStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Parem la música èpica
        mediaPlayer.stop();
    }

    /**
     * Comprobem que la carta estiga
     * en la posició corrrecta
     *
     * @param card Carta a comprobar
     * @return True - Posició correcta
     *         False - Posició incorrecta
     */
    private boolean isCorrectCard(Card card) {
        int column = card.getColumn();
        String cardName = card.getName();

        if (column == 1) {
            return cardName.equals(labelCard1.getText());
        } else if (column == 2) {
            return cardName.equals(labelCard2.getText());
        } else if (column == 3) {
            return cardName.equals(labelCard3.getText());
        } else if (column == 4) {
            return cardName.equals(labelCard4.getText());
        }
        return false;
    }

    /**
     * Afegim la imatge de baix del segon GridPane
     *
     * @param column Columna on s'afegira
     * @param pathImage Direcció de la imatge
     */
    private void setImageDown(int column, String pathImage) {
        if (pathImage == null) {
            if (column == 1) {
                imageDown1.setImage(null);
            } else if (column == 2) {
                imageDown2.setImage(null);
            } else if (column == 3) {
                imageDown3.setImage(null);
            } else if (column == 4) {
                imageDown4.setImage(null);
            }
        } else {
            if (column == 1) {
                imageDown1.setImage(new Image(pathImage));
            } else if (column == 2) {
                imageDown2.setImage(new Image(pathImage));
            } else if (column == 3) {
                imageDown3.setImage(new Image(pathImage));
            } else if (column == 4) {
                imageDown4.setImage(new Image(pathImage));
            }
        }
    }

    /**
     * Obtenim la imatge a través de la direcció
     * de la imatge
     *
     * @param urlImage Direcció de la imatge
     * @return Tornà una carta sempre i quan la trobe
     *         Si no es aixina tornarà "null"
     */
    private Card getCard(String urlImage) {
        for (Card card : cards) {
            if (card.getPathImage().equals(urlImage)) {
                return card;
            }
        }
        return null;
    }

    /**
     * Afegim la finestra principal
     *
     * @param mainStage Finestra principal
     */
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

}
