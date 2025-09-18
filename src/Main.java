import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static ECommerceSystem system;

    public static ECommerceSystem getSystem() {
        return system;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialize the system
        system = new ECommerceSystem();

        // Create admin user if it doesn't exist (AFTER system is initialized)
        if (system.getDatabase().findUserByUsername("admin") == null) {
            User admin = new User("admin", "admin123");
            system.getDatabase().addUser(admin); // Add directly to database
            system.getDatabase().saveAllData(); // Save immediately
            System.out.println("Admin user created: username='admin', password='admin123'");
        }

        // Load the first scene (Login)
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Suke.com - Login");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // This starts the JavaFX application
    }
}