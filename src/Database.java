import java.util.ArrayList;

public class Database {
    // Define file names
    private static final String USER_FILE = "users.dat";
    private static final String PRODUCT_FILE = "products.dat";
    private static final String ORDER_FILE = "orders.dat";

    private ArrayList<User> users;
    private ArrayList<Product> products;
    private ArrayList<Order> orders;

    public Database() {
        // Load all data from files when the database is created
        loadAllData();
    }

    // Load data from files
    private void loadAllData() {
        users = (ArrayList<User>) FileHandler.loadFromFile(USER_FILE);
        products = (ArrayList<Product>) FileHandler.loadFromFile(PRODUCT_FILE);
        orders = (ArrayList<Order>) FileHandler.loadFromFile(ORDER_FILE);
    }

    // Save data to files
    public void saveAllData() {
        FileHandler.saveToFile(users, USER_FILE);
        FileHandler.saveToFile(products, PRODUCT_FILE);
        FileHandler.saveToFile(orders, ORDER_FILE);
    }

    // --- User Methods ---
    public void addUser(User user) {
        users.add(user);
        saveAllData(); // Save after change
    }

    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // User not found
    }

    public ArrayList<User> getUsers() { return users; }

    // --- Product Methods ---
    public void addProduct(Product product) {
        products.add(product);
        saveAllData();
    }

    public ArrayList<Product> getProducts() { return products; }
    public ArrayList<Product> getProductsByCategory(String category) {
        ArrayList<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                result.add(product);
            }
        }
        return result;
    }

    // --- Order Methods ---
    public void addOrder(Order order) {
        orders.add(order);
        saveAllData();
    }
    public ArrayList<Order> getOrders() {
        return orders;
    }
}