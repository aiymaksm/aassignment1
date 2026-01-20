package model;

public class PackagedProduct extends Product {

    private String brand;

    public PackagedProduct(String name, double price, String category, int stock, String brand) {
        super(name, price, category, stock);
        setBrand(brand);
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

    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be empty");
        }
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }
}

