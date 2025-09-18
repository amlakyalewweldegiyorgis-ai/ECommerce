import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    @FXML
    private void handleLoginButton() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        ECommerceSystem system = Main.getSystem();
        boolean success = system.login(username, password);

        if (success) {
            // Check if the user is admin
            if ("admin".equals(username)) {
                loadAdminDashboard(); // Load admin dashboard instead of shop
            } else {
                loadShopWindow(); // Load regular shop for normal users
            }
        } else {
            messageLabel.setText("Login failed. Try again.");
        }
    }

    private void loadAdminDashboard() {
        try {
            // Close the login window
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.close();

            // Open the admin dashboard
            Stage adminStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("admin_dashboard.fxml"));
            adminStage.setTitle("Suke.com - Admin Dashboard");
            adminStage.setScene(new Scene(root, 500, 400));
            adminStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRegisterButton() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        ECommerceSystem system = Main.getSystem();
        boolean success = system.registerNewUser(username, password);

        if (success) {
            messageLabel.setText("Registration successful! Please log in.");
        } else {
            messageLabel.setText("Registration failed. Username may be taken.");
        }
    }

    private void loadShopWindow() {
        try {
            // Close the login window
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.close();

            // Open the shop window
            Stage shopStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("shop.fxml"));
            shopStage.setTitle("Suke.com - Shop");
            shopStage.setScene(new Scene(root, 800, 600));
            shopStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}