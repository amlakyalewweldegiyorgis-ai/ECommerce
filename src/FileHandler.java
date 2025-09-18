import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    // Method to save any list of objects to a file
    public static void saveToFile(List<?> list, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(list);
            System.out.println("Data successfully saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

    // Method to load a list of objects from a file
    public static List<?> loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<?>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing data found for " + filename + ". Starting fresh.");
            return new ArrayList<>(); // Return an empty list if file doesn't exist
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data from file: " + e.getMessage());
            return new ArrayList<>(); // Return an empty list on error
        }
    }
}