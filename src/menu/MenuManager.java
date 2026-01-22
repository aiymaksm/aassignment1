package menu;

import model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager implements Menu{

    private ArrayList<Product> products = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);


    public MenuManager() {
        products.add(new FreshProduct ("Milk", 2.5, "Dairy", 10,2 ));
        products.add(new PackagedProduct("Chips", 1.5, "Snacks", 30, "Lays"));
        products.add(new FreshProduct("Bread", 1.2, "Bakery", 15, 1));
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

                    case 0:
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }


    private void addFreshProduct() {
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

        products.add(new FreshProduct(name, price, category, stock, days));
        System.out.println("Fresh product added.");
    }

    private void addPackagedProduct() {
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

        products.add(new PackagedProduct(name, price, category, stock, brand));
        System.out.println("Packaged product added.");
    }

    private void viewAllProducts() {
        for (Product p : products) {
            p.displayInfo();
            System.out.println("Final price: $" + p.getFinalPrice());
            System.out.println("---------------------");
        }
    }

    private void viewFreshProductsOnly() {
        for (Product p : products) {
            if (p instanceof FreshProduct fp) {
                fp.displayInfo();
                System.out.println("Expiring soon: " + fp.isExpiringSoon());
                System.out.println("---------------------");
            }
        }
    }
}

