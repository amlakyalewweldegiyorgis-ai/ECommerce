import java.util.ArrayList;

public class ECommerceSystem {
    private Database database;
    private User currentUser;
    private ShoppingCart currentCart;

    public ECommerceSystem() {
        this.database = new Database(); // Loads all data automatically
        this.currentUser = null;
        this.currentCart = null;
    }

    // ----- User Authentication Logic -----
    public boolean login(String username, String password) {
        User user = database.findUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            currentCart = new ShoppingCart(); // Give the user a new cart on login
            System.out.println("Login successful. Welcome " + username);
            return true;
        }
        System.out.println("Login failed. Invalid username or password.");
        return false;
    }

    public void logout() {
        currentUser = null;
        currentCart = null;
        System.out.println("User logged out.");
    }

    public boolean registerNewUser(String username, String password) {
        // Check if username already exists
        if (database.findUserByUsername(username) != null) {
            System.out.println("Registration failed. Username already exists.");
            return false;
        }
        // If not, create and add the new user
        User newUser = new User(username, password);
        database.addUser(newUser);
        System.out.println("Registration successful. Please log in.");
        return true;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isUserLoggedIn() {
        return currentUser != null;
    }

    // ----- Product Browsing Logic -----
    public ArrayList<Product> getAllProducts() {
        return database.getProducts();
    }

    public ArrayList<Product> getProductsByCategory(String category) {
        return database.getProductsByCategory(category);
    }

    public Product findProductById(String id) {
        for (Product product : database.getProducts()) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    // ----- Shopping Cart Logic -----
    public void addProductToCart(Product product) {
        if (currentCart == null) {
            System.out.println("Please log in first.");
            return;
        }
        currentCart.addItem(product);
        System.out.println("Added to cart: " + product.getName());
    }

    public ShoppingCart getCurrentCart() {
        return currentCart;
    }

    // ----- Order Management Logic -----
    public Order checkoutCurrentCart() {
        if (currentCart == null || currentCart.getItems().isEmpty()) {
            System.out.println("Cannot checkout. Cart is empty.");
            return null;
        }
        if (!isUserLoggedIn()) {
            System.out.println("Please log in to checkout.");
            return null;
        }

        // 1. Create a new Order
        String newOrderId = "ORD" + (System.currentTimeMillis() % 10000); // Simple unique ID
        Order newOrder = new Order(newOrderId);

        // 2. Add all items from the cart to the order
        for (Product item : currentCart.getItems()) {
            newOrder.addProduct(item);
        }

        // 3. Add the order to the user's history and the database
        currentUser.addToOrderHistory(newOrder);
        database.addOrder(newOrder);

        // 4. Clear the cart for the next session
        currentCart.clearCart();

        System.out.println("Checkout successful! Order ID: " + newOrderId + ", Total: $" + newOrder.getTotalPrice());
        return newOrder;
    }

    // ----- Admin Logic (Basic) -----
    public void addNewProduct(String name, String id, double price, String category) {
        // In a real app, you would check if currentUser is an Admin
        Product newProduct = new Product(name, id, price, category);
        database.addProduct(newProduct);
        System.out.println("Admin: Added new product - " + name);
    }

    public Database getDatabase() {
        return database; // This gives access to the database
    }
}