package application;

import java.io.FileWriter;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {

	private Scene scene;
	
    @FXML
    private TextField emailField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    private void handleSignUp() {
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "All fields are required.");
            return;
        }
        else {
        	showAlert("Success", "Account Created.");
        }

        saveDataToFile(email, username, password);

        switchToLogin();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    private void switchToLogin() {
        try {
        	Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        	Stage stage = (Stage) emailField.getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void saveDataToFile(String email, String username, String password) {
        try (FileWriter writer = new FileWriter("users.txt", true)) {
            writer.write(username + "," + email + "," + password + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

