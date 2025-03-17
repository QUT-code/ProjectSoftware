package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import database.ConnectUserLogin;
import utility.AlertUtil;

public class SignUpController implements Initializable{
	
	private Scene scene;
	
	private FontAwesomeIconView eyeslash = new FontAwesomeIconView(FontAwesomeIcon.EYE_SLASH);
	private FontAwesomeIconView eye = new FontAwesomeIconView(FontAwesomeIcon.EYE);

	@FXML
	private Button eyeIcon;
	
	@FXML
 	private TextField signUpNameField;
 	
    @FXML
    private TextField emailField;
	
	@FXML
	private TextField signUpPasstext;
	
	@FXML
	private PasswordField signUpPassword;
	
	
	private boolean isVisible = false;

	 @FXML
	    private void togglePassword() {
	        if (isVisible) {
	        	eyeIcon.setGraphic(eyeslash);
	        	eyeslash.setStyle("-fx-font-size: 20; -fx-font-family: FontAwesome"); 
	            signUpPassword.setText(signUpPasstext.getText());
	            signUpPassword.setVisible(true);
	            signUpPasstext.setVisible(false);
	        } else {
	        	eyeIcon.setGraphic(eye);          
	            eye.setStyle("-fx-font-size: 20; -fx-font-family: FontAwesome"); 
	            signUpPasstext.setText(signUpPassword.getText());
	            signUpPasstext.setVisible(true);
	            signUpPassword.setVisible(false);
	        }
	        isVisible = !isVisible;
	    }

	 @FXML
	 public void registerUser(ActionEvent event) throws IOException {
	     String username = signUpNameField.getText();
	     String email = emailField.getText();
	     String password = signUpPassword.getText();
	     
	     if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
	         AlertUtil.showAlert("Error", "You must input your information before login.");
	         return;
	     }
	     
	     try (Connection conn = ConnectUserLogin.getConnection();
	          PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (name, email, password) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
	         
	         // Set the parameters for the PreparedStatement
	         stmt.setString(1, username);
	         stmt.setString(2, email);
	         stmt.setString(3, password);

	         // Execute the insert query
	         int rowsAffected = stmt.executeUpdate();

	         if (rowsAffected > 0) {
	             // Retrieve the generated user_id
	             try (ResultSet rs = stmt.getGeneratedKeys()) {
	                 if (rs.next()) {
	                     int userId = rs.getInt(1);  // The user_id is the first column in the generated keys
	                     System.out.println("User registered with ID: " + userId);  // Optional: print the user ID for confirmation

	                     // Optionally, you can store the user_id in a session or pass it elsewhere
	                 }
	             }
	             AlertUtil.showAlert("Success", "You have successfully registered your account.");
	             signUpToLogin(event);  // Go to login page after successful registration
	         } else {
	             AlertUtil.showAlert("Error", "Failed to register user. Please try again.");
	         }

	     } catch (SQLException e) {
	         e.printStackTrace();
	         AlertUtil.showAlert("Error", "Failed to register user. Please try again.");
	     }
	 }

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void signUpToLogin(ActionEvent event) throws IOException {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
    		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
}

