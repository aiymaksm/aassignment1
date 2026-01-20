package model;

public class FreshProduct extends Product implements Expirable {

    private int shelfLifeDays;

    public FreshProduct(String name, double price, String category, int stock, int shelfLifeDays) {
        super(name, price, category, stock);
        setShelfLifeDays(shelfLifeDays);
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

    @Override
    public boolean isExpiringSoon() {
        return shelfLifeDays <= 2;
    }

    public void reduceShelfLife() {
        if (shelfLifeDays <= 0) {
            throw new IllegalArgumentException("Shelf life cannot be negative");
        }
        shelfLifeDays--;
    }

    public void setShelfLifeDays(int shelfLifeDays) {
        if (shelfLifeDays <= 0) {
            throw new IllegalArgumentException("Shelf life must be positive");
        }
        this.shelfLifeDays = shelfLifeDays;
    }
}
