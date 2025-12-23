public class Main {

    public static void main(String[] args) {

        Product apple = new Product(1, "Apple", 0.5, 100);
        Customer customer = new Customer(1, "Aigerim", "aigerim@mail.com", 20.0);

        Sale sale = new Sale(101, apple, customer, 10);

        System.out.println("Before sale:");
        System.out.println(apple);
        System.out.println(customer);

        boolean success = sale.processSale();

        System.out.println("\nSale successful: " + success);

        System.out.println("\nAfter sale:");
        System.out.println(apple);
        System.out.println(customer);

        System.out.println("\nSale info:");
        System.out.println(sale);
        System.out.println("Total price: " + sale.calculateTotalPrice());
    }
}