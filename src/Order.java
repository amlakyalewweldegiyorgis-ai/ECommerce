import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


// 3. Order Class (Tracks a single purchase)
public class Order implements Serializable {
    private String orderId;
    private List<Product> products;
    private double totalPrice;
    private String status; // e.g., "Processing", "Delivered"

    public Order(String orderId) {
        this.orderId = orderId;
        this.products = new ArrayList<>();
        this.totalPrice = 0.0;
        this.status = "Processing";
    }

    public void addProduct(Product product) {
        this.products.add(product);
        this.totalPrice += product.getPrice();
    }

    // Getters
    public String getOrderId() { return orderId; }
    public List<Product> getProducts() { return products; }
    public double getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

