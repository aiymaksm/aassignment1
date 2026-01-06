public class PackagedProduct extends Product {

    private String brand;

    public PackagedProduct(String name, double price, String category, int stock, String brand) {
        super(name, price, category, stock); // FIRST LINE
        this.brand = brand;
    }

    @Override
    public double getFinalPrice() {
        return price; // no discount
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Packaged Product");
        System.out.println("Brand: " + brand);
    }

    // unique methods
    public String getBrand() {
        return brand;
    }

    public void changeBrand(String newBrand) {
        brand = newBrand;
    }
}
