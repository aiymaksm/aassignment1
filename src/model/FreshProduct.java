package model;

public class FreshProduct extends Product {
    private int shelfLifeDays;

    public FreshProduct(int productId, String name, double price, String category,
                        int stock, int shelfLifeDays) {
        super(productId, name, price, category, stock);
        this.shelfLifeDays = shelfLifeDays;
    }

    public FreshProduct(String name, double price, String category,
                        int stock, int shelfLifeDays) {
        super(name, price, category, stock);
        this.shelfLifeDays = shelfLifeDays;
    }

    @Override
    public double getFinalPrice() {
        return price * 0.9;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Fresh Product");
        System.out.println("Shelf life: " + shelfLifeDays + " days");
    }

    public boolean isExpiringSoon() {
        return shelfLifeDays <= 2;
    }

    public int getShelfLifeDays() {
        return shelfLifeDays;
    }

    public void setShelfLifeDays(int shelfLifeDays) {
        this.shelfLifeDays = shelfLifeDays;
    }
}