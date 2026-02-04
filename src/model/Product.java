package model;

public abstract class Product {
    protected int productId;
    protected String name;
    protected double price;
    protected String category;
    protected int stock;

    public Product(int productId, String name, double price, String category, int stock) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
    }

    public Product(String name, double price, String category, int stock) {
        this(0, name, price, category, stock);
    }

    public abstract double getFinalPrice();

    public void displayInfo() {
        System.out.println("ID: " + productId);
        System.out.println("Name: " + name);
        System.out.println("Price: $" + price);
        System.out.println("Category: " + category);
        System.out.println("Stock: " + stock);
    }

    // Getters and Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}