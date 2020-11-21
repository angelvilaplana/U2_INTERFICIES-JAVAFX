package orihuel.vilaplana.angel.drag_and_drop;

import java.net.URL;

public class Card {

    private String name;

    private URL pathImage;

    private int[] position;

    public Card(String name, URL pathImage) {
        this.name = name;
        this.pathImage = pathImage;
        this.position = new int[2];
    }

    public String getName() {
        return name;
    }

    public String getPathImage() {
        return String.valueOf(pathImage);
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public void setPosition(int grid, int column) {
        position[0] = grid;
        position[1] = column;
    }

    public int getGrid() {
        return position[0];
    }

    public int getColumn() {
        return position[1];
    }

}
