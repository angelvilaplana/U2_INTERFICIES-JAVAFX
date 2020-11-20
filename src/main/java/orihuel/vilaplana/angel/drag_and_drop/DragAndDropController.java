package orihuel.vilaplana.angel.drag_and_drop;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

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

    @FXML
    private void initialize() {
        initializeCards();
        initializeImagesDrag();
        initializeLabels();
        initializeImagesDrop();
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
        Card card;

        randomImage = randomImages.get(random.nextInt(randomImages.size()));
        setImageDrag(imageDrag1, randomImage.getPathImage(), randomImage.getName());
        card = getCard(randomImage.getPathImage());
        if (card != null) {
            card.setInitialColumn(1);
        }
        randomImages.remove(randomImage);

        randomImage = randomImages.get(random.nextInt(randomImages.size()));
        setImageDrag(imageDrag2, randomImage.getPathImage(), randomImage.getName());
        card = getCard(randomImage.getPathImage());
        if (card != null) {
            card.setInitialColumn(2);
        }
        randomImages.remove(randomImage);

        randomImage = randomImages.get(random.nextInt(randomImages.size()));
        setImageDrag(imageDrag3, randomImage.getPathImage(), randomImage.getName());
        card = getCard(randomImage.getPathImage());
        if (card != null) {
            card.setInitialColumn(3);
        }
        randomImages.remove(randomImage);

        randomImage = randomImages.get(0);
        setImageDrag(imageDrag4, randomImage.getPathImage(), randomImage.getName());
        card = getCard(randomImage.getPathImage());
        if (card != null) {
            card.setInitialColumn(4);
        }
    }

    private void setImageDrag(ImageView imageDrag, String pathImage, String nameCard) {
        imageDrag.setImage(new Image(pathImage));
        imageDrag.setOnMouseEntered(e -> {
            if (imageDrag.getImage() != null && imageDrag.getImage().getUrl() != null) {
                imageDrag.setCursor(Cursor.HAND);
            }
        });
        imageDrag.setOnDragDetected(e -> {
            if (imageDrag.getImage() != null && imageDrag.getImage().getUrl() != null) {
                imageDrag.setCursor(Cursor.CLOSED_HAND);
                handleDragImage(imageDrag, nameCard);
            }
        });
        imageDrag.setOnDragDone(e -> {
            if (e.isAccepted()) {
                imageDrag.setImage(null);
                imageDrag.setCursor(Cursor.DEFAULT);
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

    private void handleDragImage(ImageView imageDrag, String cardName) {
        Dragboard dragboard = imageDrag.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        Image image = new Image(imageDrag.getImage().getUrl(), imageDrag.getFitWidth(), imageDrag.getFitHeight(), true, true);
        content.putImage(image);
        content.putUrl(image.getUrl());
        content.putString(cardName);
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
            if(dragboard.hasImage() && (imageDrop.getImage() == null || !imageDrop.getImage().getUrl().equals(dragboard.getUrl()))) {
                if (imageDrop.getImage() != null) {
                    returnImageInitialPosition(imageDrop.getImage().getUrl());
                }

                String cardName = dragboard.getString();
                if (isCorrectCard(cardName, column)) {
                    setImageCorrectDrag(imageDrop, dragboard.getUrl());
                } else {
                    setImageDrag(imageDrop, dragboard.getUrl(), dragboard.getString());
                }
                e.setDropCompleted(true);
            } else {
                e.setDropCompleted(false);
            }
            e.consume();
        });
    }

    private boolean isCorrectCard(String cardName, int column) {
        if (column == 1) {
            if (cardName.equals(labelCard1.getText())) {
                imageDown1.setImage(new Image(DragAndDrop.class.getResource("images/correct.png").toString()));
                return true;
            } else {
                imageDown1.setImage(new Image(DragAndDrop.class.getResource("images/incorrect.png").toString()));
                return false;
            }
        } else if (column == 2) {
            if (cardName.equals(labelCard2.getText())) {
                imageDown2.setImage(new Image(DragAndDrop.class.getResource("images/correct.png").toString()));
                return true;
            } else {
                imageDown2.setImage(new Image(DragAndDrop.class.getResource("images/incorrect.png").toString()));
                return false;
            }
        } else if (column == 3) {
            if (cardName.equals(labelCard3.getText())) {
                imageDown3.setImage(new Image(DragAndDrop.class.getResource("images/correct.png").toString()));
                return true;
            } else {
                imageDown3.setImage(new Image(DragAndDrop.class.getResource("images/incorrect.png").toString()));
                return false;
            }
        } else if (column == 4) {
            if (cardName.equals(labelCard4.getText())) {
                imageDown4.setImage(new Image(DragAndDrop.class.getResource("images/correct.png").toString()));
                return true;
            } else {
                imageDown4.setImage(new Image(DragAndDrop.class.getResource("images/incorrect.png").toString()));
                return false;
            }
        } else {
            return false;
        }
    }

    private void returnImageInitialPosition(String url) {
        Card card = getCard(url);
        int column = card.getInitialColumn();

        if (column == 1) {
            imageDrag1.setImage(new Image(url));
        } else if (column == 2) {
            imageDrag2.setImage(new Image(url));
        } else if (column == 3) {
            imageDrag3.setImage(new Image(url));
        } else if (column == 4) {
            imageDrag4.setImage(new Image(url));
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
