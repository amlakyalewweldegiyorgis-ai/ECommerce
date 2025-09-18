import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


// 2. User Class
public class User implements Serializable {
    private String username;
    private String password;
    private List<Order> orderHistory;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.orderHistory = new ArrayList<>();
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<Order> getOrderHistory() { return orderHistory; }
    public void addToOrderHistory(Order order) { this.orderHistory.add(order); }
}

