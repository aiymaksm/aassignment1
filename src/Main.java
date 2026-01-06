import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Product> products = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Initial data
        products.add(new FreshProduct("Milk", 2.5, "Dairy", 10, 2));
        products.add(new PackagedProduct("Chips", 1.5, "Snacks", 30, "Lays"));
        products.add(new FreshProduct("Bread", 1.2, "Bakery", 15, 1));

        boolean running = true;

        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    addFreshProduct();
                    break;
                case 3:
                    addPackagedProduct();
                    break;
                case 4:
                    viewAllProducts();
                    break;
                case 5:
                    demonstratePolymorphism();
                    break;
                case 6:
                    viewFreshProductsOnly();
                    break;
                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n=================================");
        System.out.println(" GROCERY STORE MANAGEMENT SYSTEM");
        System.out.println("=================================");
        System.out.println("1. Add Product (General)");
        System.out.println("2. Add Fresh Product");
        System.out.println("3. Add Packaged Product");
        System.out.println("4. View All Products");
        System.out.println("5. Demonstrate Polymorphism");
        System.out.println("6. View Fresh Products Only");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private static void addProduct() {
        System.out.println("\n--- ADD PRODUCT ---");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Category: ");
        String category = scanner.nextLine();

        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        Product product = new Product(name, price, category, stock);
        products.add(product);

        System.out.println("Product added successfully.");
    }

    private static void addFreshProduct() {
        System.out.println("\n--- ADD FRESH PRODUCT ---");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Category: ");
        String category = scanner.nextLine();

        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Shelf life (days): ");
        int days = scanner.nextInt();
        scanner.nextLine();

        Product fresh = new FreshProduct(name, price, category, stock, days);
        products.add(fresh);

        System.out.println("Fresh product added successfully.");
    }

    private static void addPackagedProduct() {
        System.out.println("\n--- ADD PACKAGED PRODUCT ---");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Category: ");
        String category = scanner.nextLine();

        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Brand: ");
        String brand = scanner.nextLine();

        Product packaged = new PackagedProduct(name, price, category, stock, brand);
        products.add(packaged);

        System.out.println("Packaged product added successfully.");
    }

    private static void viewAllProducts() {
        System.out.println("\n--- ALL PRODUCTS ---");

        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        int index = 1;
        for (Product p : products) {
            System.out.println(index + ". ");
            p.displayInfo();
            System.out.println("Final price: $" + p.getFinalPrice());
            index++;
        }
    }

    private static void demonstratePolymorphism() {
        System.out.println("\n--- POLYMORPHISM DEMO ---");

        for (Product p : products) {
            p.displayInfo(); // overridden method
        }

        System.out.println("Same method, different behavior = Polymorphism.");
    }

    private static void viewFreshProductsOnly() {
        System.out.println("\n--- FRESH PRODUCTS ONLY ---");

        int count = 0;

        for (Product p : products) {
            if (p instanceof FreshProduct) {
                FreshProduct fp = (FreshProduct) p; // downcasting
                count++;
                System.out.println(count + ". ");
                fp.displayInfo();
                System.out.println("Expiring soon: " + fp.isExpiringSoon());
            }
        }

        if (count == 0) {
            System.out.println("No fresh products found.");
        }
    }
}
