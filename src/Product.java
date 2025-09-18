import java.io.Serializable;


// 1. Product Class
public class Product implements Serializable {
    private String name;
    private String id;
    private double price;
    private String category;

    public Product(String name, String id, double price, String category) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.category = category;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}

