import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CartController {

    @FXML private ListView<Product> cartListView;
    @FXML private Label totalLabel;

    private ECommerceSystem system;

    @FXML
    public void initialize() {
        system = Main.getSystem();
        refreshCartView();
    }

    private void refreshCartView() {
        // Clear and reload cart items
        cartListView.getItems().clear();
        if (system.getCurrentCart() != null) {
            cartListView.getItems().addAll(system.getCurrentCart().getItems());
        }

        // Set custom cell factory to display products
        cartListView.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
            @Override
            public ListCell<Product> call(ListView<Product> listView) {
                return new ListCell<Product>() {
                    @Override
                    protected void updateItem(Product product, boolean empty) {
                        super.updateItem(product, empty);
                        if (empty || product == null) {
                            setText(null);
                        } else {
                            setText(product.getName() + " - $" + product.getPrice());
                        }
                    }
                };
            }
        });

        // Update total price
        if (system.getCurrentCart() != null) {
            double total = system.getCurrentCart().getTotalPrice();
            totalLabel.setText("Total: $" + String.format("%.2f", total));
        } else {
            totalLabel.setText("Total: $0.00");
        }
    }

    @FXML
    private void handleContinueShopping() {
        // Close the cart window
        Stage stage = (Stage) cartListView.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCheckout() {
        if (system.getCurrentCart() != null && !system.getCurrentCart().getItems().isEmpty()) {
            Order newOrder = system.checkoutCurrentCart();
            if (newOrder != null) {
                System.out.println("Checkout successful! Order: " + newOrder.getOrderId());

                // Show success message
                totalLabel.setText("Checkout successful! Order #" + newOrder.getOrderId());

                // Refresh the cart view (should be empty now)
                refreshCartView();
            }
        } else {
            totalLabel.setText("Cannot checkout. Cart is empty.");
        }
    }
}