package model;

public class PackagedProduct extends Product {
    private String brand;

    public PackagedProduct(int productId, String name, double price, String category,
                           int stock, String brand) {
        super(productId, name, price, category, stock);
        this.brand = brand;
    }

    public PackagedProduct(String name, double price, String category,
                           int stock, String brand) {
        super(name, price, category, stock);
        this.brand = brand;
    }

    @Override
    public double getFinalPrice() {
        return price;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Packaged Product");
        System.out.println("Brand: " + brand);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}