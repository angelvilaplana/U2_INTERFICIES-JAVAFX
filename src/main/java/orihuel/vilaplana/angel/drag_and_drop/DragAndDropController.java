package orihuel.vilaplana.angel.drag_and_drop;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
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

    private List<Card> cards;

    private int movements;

    private int cardsCorrects;

    @FXML
    private void initialize() {
        initializeCards();
        initializeImagesDrag();
        initializeLabels();
        initializeImagesDrop();
        movements = 0;
        cardsCorrects = 0;
    }

    private void initializeCards() {
        cards = new ArrayList<>();
        cards.add(new Card("Hulk", DragAndDrop.class.getResource("images/hulk.png")));
        cards.add(new Card("Ironman", DragAndDrop.class.getResource("images/ironman.png")));
        cards.add(new Card("Spiderman", DragAndDrop.class.getResource("images/spiderman.png")));
        cards.add(new Card("Thor", DragAndDrop.class.getResource("images/thor.png")));
    }

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

    private void setImageDrag(ImageView imageDrag, String pathImage) {
        imageDrag.setImage(new Image(pathImage));

        imageDrag.setOnMouseEntered(e -> {
            if (imageDrag.getImage() != null && imageDrag.getImage().getUrl() != null) {
                imageDrag.setCursor(Cursor.HAND);
            }
        });

        imageDrag.setOnDragDetected(e -> {
            if (imageDrag.getImage() != null && imageDrag.getImage().getUrl() != null) {
                imageDrag.setCursor(Cursor.CLOSED_HAND);
                handleDragImage(imageDrag);
            }
        });

        imageDrag.setOnDragDone(e -> {
            if (e.isAccepted()) {
                Dragboard dragboard = e.getDragboard();

                if (dragboard.getUrl() != null) {
                    Card card = getCard(dragboard.getUrl());

                    if (card.getGrid() == 2 && isCorrectCard(card)) {
                        setImageCorrectDrag(imageDrag, card.getPathImage());
                        setImageDown(card.getColumn(), DragAndDrop.class.getResource("images/correct.png").toString());
                        cardsCorrects++;
                        if (cardsCorrects == cards.size()) {
                            endGame();
                        }
                    } else {
                        setImageDrag(imageDrag, card.getPathImage());
                    }
                } else {
                    imageDrag.setImage(null);
                }

                imageDrag.setCursor(Cursor.DEFAULT);
                movements++;
            }
        });
    }

    private void setImageCorrectDrag(ImageView imageDrag, String pathImage) {
        imageDrag.setImage(new Image(pathImage));
        imageDrag.setOnMouseEntered(e -> imageDrag.setCursor(Cursor.DEFAULT));
        imageDrag.setOnDragDetected(e -> {});
        imageDrag.setOnDragDone(e -> {});
        imageDrag.setOnDragOver(e -> {});
        imageDrag.setOnDragDropped(e -> {});
    }

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

    private void initializeImagesDrop() {
        handleDropImage(imageDrop1, 1);
        handleDropImage(imageDrop2, 2);
        handleDropImage(imageDrop3, 3);
        handleDropImage(imageDrop4, 4);
    }

    private void handleDragImage(ImageView imageDrag) {
        Dragboard dragboard = imageDrag.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        Image image = new Image(imageDrag.getImage().getUrl(), imageDrag.getFitWidth(), imageDrag.getFitHeight(), true, true);
        content.putImage(image);
        content.putUrl(image.getUrl());
        dragboard.setContent(content);
    }

    private void handleDropImage(ImageView imageDrop, int column) {
        imageDrop.setOnDragOver(e -> {
            Dragboard dragboard = e.getDragboard();
            if(dragboard.hasImage()){
                e.acceptTransferModes(TransferMode.MOVE);
            }
            e.consume();
        });

        imageDrop.setOnDragDropped(e -> {
            Dragboard dragboard = e.getDragboard();
            Card newCard = getCard(dragboard.getUrl());

            if (dragboard.hasImage() && (imageDrop.getImage() == null || !imageDrop.getImage().getUrl().equals(dragboard.getUrl()))) {
                int[] positionDragBoard = {2, column};

                ClipboardContent content = new ClipboardContent();
                if (imageDrop.getImage() != null) {
                    Card card = getCard(imageDrop.getImage().getUrl());
                    positionDragBoard = card.getPosition();
                    card.setPosition(newCard.getPosition());
                    content.putUrl(card.getPathImage());
                } else if (newCard.getGrid() == 2) {
                    setImageDown(newCard.getColumn(), null);
                }

                dragboard.setContent(content);

                newCard.setPosition(positionDragBoard);

                if (isCorrectCard(newCard)) {
                    setImageCorrectDrag(imageDrop, newCard.getPathImage());
                    setImageDown(newCard.getColumn(), DragAndDrop.class.getResource("images/correct.png").toString());
                    cardsCorrects++;
                    if (cardsCorrects == cards.size()) {
                        endGame();
                    }
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

    private void endGame() {
        ImageView image = new ImageView(DragAndDrop.class.getResource("images/correct.png").toString());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setGraphic(image);
        alert.showAndWait();
    }

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

    private Card getCard(String urlImage) {
        for (Card card : cards) {
            if (card.getPathImage().equals(urlImage)) {
                return card;
            }
        }
        return null;
    }

}
