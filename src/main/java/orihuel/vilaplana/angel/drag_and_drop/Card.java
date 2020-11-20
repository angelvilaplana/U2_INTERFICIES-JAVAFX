package orihuel.vilaplana.angel.drag_and_drop;

import java.net.URL;

public class Card {

    private String name;

    private URL pathImage;

    private int initialColumn;

    public Card(String name, URL pathImage) {
        this.name = name;
        this.pathImage = pathImage;
    }

    public String getName() {
        return name;
    }

    public String getPathImage() {
        return String.valueOf(pathImage);
    }

    public void setInitialColumn(int positionColumn) {
        this.initialColumn = positionColumn;
    }

    public int getInitialColumn() {
        return initialColumn;
    }

}
