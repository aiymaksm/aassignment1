public class FreshProduct extends Product {

    private int shelfLifeDays;

    public FreshProduct(String name, double price, String category, int stock, int shelfLifeDays) {
        super(name, price, category, stock); // FIRST LINE
        this.shelfLifeDays = shelfLifeDays;
    }

    @Override
    public double getFinalPrice() {
        return price * 0.9; // 10% discount
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Fresh Product");
        System.out.println("Shelf life: " + shelfLifeDays + " days");
    }

    // unique methods
    public boolean isExpiringSoon() {
        return shelfLifeDays <= 2;
    }

    public void reduceShelfLife() {
        shelfLifeDays--;
    }
}
