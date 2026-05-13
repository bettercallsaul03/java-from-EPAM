package model;

public class Airplane {
    private String model;
    private String origin;
    private int price;

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    @Override
    public String toString() {
        return model + " | " + origin + " | " + price;
    }
}
