public class Product {

    protected String name;
    protected double price;
    protected String category;
    protected int stock;


    public Product(String name, double price, String category, int stock) {
        this.name = name;
        setPrice(price);   // validation
        setStock(stock);   // validation
        this.category = category;
    }


    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        } else {
            System.out.println("Invalid price! Price must be positive.");
        }
    }


    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        } else {
            System.out.println("Invalid stock! Stock cannot be negative.");
        }
    }

    public double getFinalPrice() {
        return price;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Category: " + category);
        System.out.println("Price: $" + price);
        System.out.println("Stock: " + stock);
    }

    public void restock(int amount) {
        stock += amount;
        System.out.println(name + " restocked. New stock: " + stock);
    }
}
