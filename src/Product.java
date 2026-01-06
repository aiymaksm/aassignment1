public class Product {

    protected String name;
    protected double price;
    protected String category;
    protected int stock;

    // parameterized constructor
    public Product(String name, double price, String category, int stock) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
    }

    // method 1
    public double getFinalPrice() {
        return price;
    }

    // method 2
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Category: " + category);
        System.out.println("Price: $" + price);
        System.out.println("Stock: " + stock);
    }

    // method 3 (action method)
    public void restock(int amount) {
        stock += amount;
        System.out.println(name + " restocked. New stock: " + stock);
    }
}
