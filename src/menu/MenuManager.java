package menu;

import model.Product;
import model.FreshProduct;
import model.PackagedProduct;
//import database.ProductDAO;
import dao.ProductDAO;
import java.util.List;
import java.util.Scanner;

public class MenuManager implements Menu {
    private Scanner scanner;
    private ProductDAO productDAO;

    public MenuManager() {
        this.scanner = new Scanner(System.in);
        this.productDAO = new ProductDAO();
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=================================");
        System.out.println(" GROCERY STORE MANAGEMENT SYSTEM");
        System.out.println("=================================");
        System.out.println("1. Add Fresh Product");
        System.out.println("2. Add Packaged Product");
        System.out.println("3. View All Products");
        System.out.println("4. View Fresh Products Only");
        System.out.println("5. Update Product");
        System.out.println("6. Delete Product");
        System.out.println("7. Search by Name");
        System.out.println("8. Filter by Category");
        System.out.println("9. Filter by Price Range");
        System.out.println("10. View Low Stock");
        System.out.println("11. Polymorphism Demo");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addFreshProduct();
                        break;
                    case 2:
                        addPackagedProduct();
                        break;
                    case 3:
                        viewAllProducts();
                        break;
                    case 4:
                        viewFreshProductsOnly();
                        break;
                    case 5:
                        updateProduct();
                        break;
                    case 6:
                        deleteProduct();
                        break;
                    case 7:
                        searchByName();
                        break;
                    case 8:
                        filterByCategory();
                        break;
                    case 9:
                        filterByPriceRange();
                        break;
                    case 10:
                        viewLowStock();
                        break;
                    case 11:
                        polymorphismDemo();
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        scanner.close();
        System.out.println("Goodbye!");
    }

    private void addFreshProduct() {
        System.out.println("\n--- Add Fresh Product ---");

        try {
            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Price: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Category: ");
            String category = scanner.nextLine();

            System.out.print("Stock: ");
            int stock = Integer.parseInt(scanner.nextLine());

            System.out.print("Shelf life (days): ");
            int days = Integer.parseInt(scanner.nextLine());

            FreshProduct product = new FreshProduct(name, price, category, stock, days);

            if (productDAO.addFreshProduct(product)) {
                System.out.println("Fresh product added successfully!");
            } else {
                System.out.println("Failed to add fresh product!");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addPackagedProduct() {
        System.out.println("\n--- Add Packaged Product ---");

        try {
            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Price: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Category: ");
            String category = scanner.nextLine();

            System.out.print("Stock: ");
            int stock = Integer.parseInt(scanner.nextLine());

            System.out.print("Brand: ");
            String brand = scanner.nextLine();

            PackagedProduct product = new PackagedProduct(name, price, category, stock, brand);

            if (productDAO.addPackagedProduct(product)) {
                System.out.println("Packaged product added successfully!");
            } else {
                System.out.println("Failed to add packaged product!");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllProducts() {
        System.out.println("\n--- All Products ---");

        List<Product> products = productDAO.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No products in database.");
        } else {
            for (Product p : products) {
                p.displayInfo();
                System.out.println("Final price: $" + p.getFinalPrice());

                if (p instanceof FreshProduct) {
                    FreshProduct fp = (FreshProduct) p;
                    System.out.println("Expiring soon: " + fp.isExpiringSoon());
                }

                System.out.println("---------------------");
            }
            System.out.println("Total: " + products.size() + " products");
        }
    }

    private void viewFreshProductsOnly() {
        System.out.println("\n--- Fresh Products Only ---");

        List<FreshProduct> freshProducts = productDAO.getFreshProducts();

        if (freshProducts.isEmpty()) {
            System.out.println("No fresh products in database.");
        } else {
            for (FreshProduct fp : freshProducts) {
                fp.displayInfo();
                System.out.println("Expiring soon: " + fp.isExpiringSoon());
                System.out.println("Final price with discount: $" + fp.getFinalPrice());
                System.out.println("---------------------");
            }
        }
    }

    private void updateProduct() {
        System.out.println("\n--- Update Product ---");

        System.out.print("Enter Product ID to update: ");
        int productId = Integer.parseInt(scanner.nextLine());

        Product existingProduct = productDAO.getProductById(productId);

        if (existingProduct == null) {
            System.out.println("Product not found!");
            return;
        }

        System.out.println("\nCurrent product info:");
        existingProduct.displayInfo();

        try {
            System.out.println("\nEnter new values (press Enter to keep current):");

            System.out.print("Name [" + existingProduct.getName() + "]: ");
            String newName = scanner.nextLine();
            if (newName.trim().isEmpty()) {
                newName = existingProduct.getName();
            }

            System.out.print("Price [" + existingProduct.getPrice() + "]: ");
            String priceInput = scanner.nextLine();
            double newPrice = priceInput.trim().isEmpty() ?
                    existingProduct.getPrice() :
                    Double.parseDouble(priceInput);

            System.out.print("Category [" + existingProduct.getCategory() + "]: ");
            String newCategory = scanner.nextLine();
            if (newCategory.trim().isEmpty()) {
                newCategory = existingProduct.getCategory();
            }

            System.out.print("Stock [" + existingProduct.getStock() + "]: ");
            String stockInput = scanner.nextLine();
            int newStock = stockInput.trim().isEmpty() ?
                    existingProduct.getStock() :
                    Integer.parseInt(stockInput);

            boolean success;

            if (existingProduct instanceof FreshProduct) {
                FreshProduct fresh = (FreshProduct) existingProduct;
                System.out.print("Shelf Life [" + fresh.getShelfLifeDays() + "]: ");
                String shelfInput = scanner.nextLine();
                int newShelfLife = shelfInput.trim().isEmpty() ?
                        fresh.getShelfLifeDays() :
                        Integer.parseInt(shelfInput);

                FreshProduct updated = new FreshProduct(productId, newName, newPrice,
                        newCategory, newStock, newShelfLife);
                success = productDAO.updateProduct(updated);

            } else {
                PackagedProduct packaged = (PackagedProduct) existingProduct;
                System.out.print("Brand [" + packaged.getBrand() + "]: ");
                String newBrand = scanner.nextLine();
                if (newBrand.trim().isEmpty()) {
                    newBrand = packaged.getBrand();
                }

                PackagedProduct updated = new PackagedProduct(productId, newName, newPrice,
                        newCategory, newStock, newBrand);
                success = productDAO.updateProduct(updated);
            }

            if (success) {
                System.out.println("Product updated successfully!");
            } else {
                System.out.println("Failed to update product!");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteProduct() {
        System.out.println("\n--- Delete Product ---");

        System.out.print("Enter Product ID to delete: ");
        int productId = Integer.parseInt(scanner.nextLine());

        Product product = productDAO.getProductById(productId);

        if (product == null) {
            System.out.println("Product not found!");
            return;
        }

        System.out.println("\nProduct to delete:");
        product.displayInfo();

        System.out.print("\nAre you sure? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            if (productDAO.deleteProduct(productId)) {
                System.out.println("Product deleted successfully!");
            } else {
                System.out.println("Failed to delete product!");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private void searchByName() {
        System.out.println("\n--- Search by Name ---");

        System.out.print("Enter product name (full or partial): ");
        String searchTerm = scanner.nextLine();

        List<Product> results = productDAO.searchByName(searchTerm);

        if (results.isEmpty()) {
            System.out.println("No products found matching: " + searchTerm);
        } else {
            System.out.println("Found " + results.size() + " product(s):");
            for (Product p : results) {
                p.displayInfo();
                System.out.println("---------------------");
            }
        }
    }

    private void filterByCategory() {
        System.out.println("\n--- Filter by Category ---");

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        List<Product> results = productDAO.searchByCategory(category);

        if (results.isEmpty()) {
            System.out.println("No products found in category: " + category);
        } else {
            System.out.println("Products in category '" + category + "':");
            for (Product p : results) {
                p.displayInfo();
                System.out.println("---------------------");
            }
        }
    }

    private void filterByPriceRange() {
        System.out.println("\n--- Filter by Price Range ---");

        try {
            System.out.print("Minimum price: ");
            double minPrice = Double.parseDouble(scanner.nextLine());

            System.out.print("Maximum price: ");
            double maxPrice = Double.parseDouble(scanner.nextLine());

            List<Product> results = productDAO.searchByPriceRange(minPrice, maxPrice);

            if (results.isEmpty()) {
                System.out.println("No products found between $" + minPrice + " and $" + maxPrice);
            } else {
                System.out.println("Products between $" + minPrice + " and $" + maxPrice + ":");
                for (Product p : results) {
                    p.displayInfo();
                    System.out.println("---------------------");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numbers for price.");
        }
    }

    private void viewLowStock() {
        System.out.println("\n--- Low Stock Products ---");

        System.out.print("Enter low stock threshold: ");
        int threshold = Integer.parseInt(scanner.nextLine());

        List<Product> results = productDAO.getLowStockProducts(threshold);

        if (results.isEmpty()) {
            System.out.println("No products with stock less than " + threshold);
        } else {
            System.out.println("Low stock products (stock < " + threshold + "):");
            for (Product p : results) {
                p.displayInfo();
                System.out.println("---------------------");
            }
        }
    }

    private void polymorphismDemo() {
        System.out.println("\n--- Polymorphism Demo ---");

        List<Product> products = productDAO.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No products for demo.");
            return;
        }

        System.out.println("Demonstrating polymorphism with " + products.size() + " products:");
        System.out.println("================================");

        double totalValue = 0;
        for (Product p : products) {
            System.out.println("Product: " + p.getName());
            System.out.println("Class: " + p.getClass().getSimpleName());
            System.out.println("Original Price: $" + p.getPrice());
            System.out.println("Final Price: $" + p.getFinalPrice());

            if (p instanceof FreshProduct) {
                System.out.println("This product can expire!");
            }

            totalValue += p.getFinalPrice() * p.getStock();
            System.out.println("================================");
        }

        System.out.println("Total inventory value: $" + String.format("%.2f", totalValue));
    }
}