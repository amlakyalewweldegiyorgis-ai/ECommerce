import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.io.IOException;

public class ShopController {

    @FXML private ListView<Product> productListView;
    @FXML private Label welcomeLabel;
    private ECommerceSystem system;

    @FXML
    public void initialize() {
        system = Main.getSystem();
        welcomeLabel.setText("Welcome, " + system.getCurrentUser().getUsername() + "!");
        productListView.getItems().addAll(system.getAllProducts());

        // Set custom cell factory with Add to Cart button
        productListView.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
            @Override
            public ListCell<Product> call(ListView<Product> listView) {
                return new ListCell<Product>() {
                    private final HBox hbox = new HBox(10);
                    private final Label label = new Label();
                    private final Button addButton = new Button("Add to Cart");

                    {
                        // Setup the layout
                        hbox.getChildren().addAll(label, addButton);

                        // Setup the button action
                        addButton.setOnAction(event -> {
                            Product product = getItem();
                            if (product != null) {
                                system.addProductToCart(product);
                                System.out.println("Added to cart: " + product.getName());
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Product product, boolean empty) {
                        super.updateItem(product, empty);
                        if (empty || product == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            label.setText(product.getName() + " - $" + product.getPrice() + " (" + product.getCategory() + ")");
                            setGraphic(hbox);
                        }
                    }
                };
            }
        });
    }

    @FXML
    private void handleViewCartButton() {
        try {
            // Load the cart view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
            Parent root = loader.load();

            // Create new window for cart
            Stage cartStage = new Stage();
            cartStage.setTitle("Suke.com - Shopping Cart");
            cartStage.setScene(new Scene(root, 500, 400));
            cartStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading cart view: " + e.getMessage());
        }
    }

    @FXML
    private void handleLogoutButton() {
        try {
            system.logout();
            // Close shop window
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.close();

            // Reopen login window
            Stage loginStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            loginStage.setTitle("Suke.com - Login");
            loginStage.setScene(new Scene(root, 600, 400));
            loginStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}