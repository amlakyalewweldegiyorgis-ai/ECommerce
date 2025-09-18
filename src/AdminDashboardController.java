import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminDashboardController {

    @FXML private TextField productNameField;
    @FXML private TextField productIdField;
    @FXML private TextField productPriceField;
    @FXML private TextField productCategoryField;
    @FXML private Label adminMessageLabel;

    private ECommerceSystem system;

    @FXML
    public void initialize() {
        system = Main.getSystem();
    }

    @FXML
    private void handleAddProduct() {
        try {
            String name = productNameField.getText();
            String id = productIdField.getText();
            double price = Double.parseDouble(productPriceField.getText());
            String category = productCategoryField.getText();

            // Add the new product
            system.addNewProduct(name, id, price, category);

            adminMessageLabel.setText("Product added successfully: " + name);

            // Clear the form
            productNameField.clear();
            productIdField.clear();
            productPriceField.clear();
            productCategoryField.clear();

        } catch (NumberFormatException e) {
            adminMessageLabel.setText("Error: Please enter a valid price.");
        } catch (Exception e) {
            adminMessageLabel.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleBackToShop() {
        try {
            // Close admin dashboard
            Stage stage = (Stage) productNameField.getScene().getWindow();
            stage.close();

            // Open shop window
            Stage shopStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("shop.fxml"));
            shopStage.setTitle("Suke.com - Shop");
            shopStage.setScene(new Scene(root, 800, 600));
            shopStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}