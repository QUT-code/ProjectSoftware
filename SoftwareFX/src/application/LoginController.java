package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LoginController {

    private Scene scene;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    private static final HashMap<String, String> userDatabase = new HashMap<>();

    static {
        loadUserData(); 
    }

    private static void loadUserData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(","); 
                if (userData.length >= 3) { 
                    String username = userData[0]; 
                    String email = userData[1]; 
                    String password = userData[2]; 
                    userDatabase.put(username, password); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogin() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Username and Password required.");
            return;
        }

        if (checkCredentials(username, password)) {
            showAlert("Success", "Login successful!");
            loadToMainScreen();
            
        } else {
            showAlert("Error", "Invalid username or password.");
        }
    }

    private boolean checkCredentials(String username, String password) {
        return userDatabase.containsKey(username) && userDatabase.get(username).equals(password);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void switchToSignUp() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignUpScreen.fxml"));
        Stage stage = (Stage) usernameField.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void loadToMainScreen() throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
    	Stage stage = (Stage) usernameField.getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    
    public void switchToCreate(ActionEvent event) throws IOException {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("CreateScreen.fxml"));
    		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
}
