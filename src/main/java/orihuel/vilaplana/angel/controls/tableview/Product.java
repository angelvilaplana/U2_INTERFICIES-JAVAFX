package orihuel.vilaplana.angel.controls.tableview;

public class Product {

    private String name;

    private float height;

    private float width;

    private float longer;

    private float weight;

    public Product(String name, float height, float width, float longer, float weight) {
        this.name = name;
        this.height = height;
        this.width = width;
        this.longer = longer;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getLonger() {
        return longer;
    }

    public void setLonger(float longer) {
        this.longer = longer;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

}
