public class Sale {

    private int saleId;
    private Product product;
    private Customer customer;
    private int quantity;


    public Sale(int saleId, Product product, Customer customer, int quantity) {
        this.saleId = saleId;
        this.product = product;
        this.customer = customer;
        this.quantity = quantity;
    }


    public int getSaleId() {
        return saleId;
    }
    public Product getProduct() {
        return product;
    }
    public Customer getCustomer() {
        return customer;
    }
    public int getQuantity() {
        return quantity;
    }


    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Logic methods
    public double calculateTotalPrice() {
        return product.getPrice() * quantity;
    }

    public boolean processSale() {
        double total = calculateTotalPrice();

        if (product.getStockQuantity() >= quantity && customer.canAfford(total)) {
            product.reduceStock(quantity);
            customer.deductBalance(total);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Sale{saleId=" + saleId +
                ", product=" + product.getName() +
                ", customer=" + customer.getName() +
                ", quantity=" + quantity +
                '}';
    }
}
