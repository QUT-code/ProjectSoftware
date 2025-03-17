package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class ControllerLogin implements Initializable{
	
	private Scene scene;
	
	private FontAwesomeIconView eyeslash = new FontAwesomeIconView(FontAwesomeIcon.EYE_SLASH);
	private FontAwesomeIconView eye = new FontAwesomeIconView(FontAwesomeIcon.EYE);
	
	@FXML
	private Button eyeIcon;
	
	@FXML
	private TextField loginNameField;
	
	@FXML
	private TextField passtext;
	
	@FXML
	private PasswordField password;
	
	private boolean isVisible = false;

    @FXML
    private void togglePassword() {
        if (isVisible) {
        	eyeIcon.setGraphic(eyeslash);
        	eyeslash.setStyle("-fx-font-size: 20; -fx-font-family: FontAwesome"); 
            password.setText(passtext.getText());
            password.setVisible(true);
            passtext.setVisible(false);
        } else {
        	eyeIcon.setGraphic(eye);          
            eye.setStyle("-fx-font-size: 20; -fx-font-family: FontAwesome"); 
            passtext.setText(password.getText());
            passtext.setVisible(true);
            password.setVisible(false);
        }
        isVisible = !isVisible;
    }
    @FXML
    private TextField loginUsername;

    @FXML
	public void loginUser(ActionEvent event) throws IOException {
		String name = loginNameField.getText();
		String pass = password.getText();
		
		if (name.isEmpty() || pass.isEmpty()) {
			AlertUtil.showAlert("Error", "All field are required!");
			return;
		}
		
		try (Connection conn = ConnectUserLogin.getConnection();
			PreparedStatement stmt = conn.prepareStatement("Select * From users where name = ? And password = ?" )){
			stmt.setString(1, name);
			stmt.setString(2, pass);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				AlertUtil.showAlert("Success", "Login Successful");
				loginToMain(event);
			}
			else {
				AlertUtil.showAlert("Failed to login", "Invalid password or username");
			}
		}catch (SQLException e) {
			e.printStackTrace();
			AlertUtil.showAlert("Error", "Login Failed");
		}
	}
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
	
	@FXML
	public void loginToSignUp(ActionEvent event) throws IOException {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/fxml/SignUp.fxml"));
    		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
	
	@FXML
	public void loginToMain(ActionEvent event) throws IOException {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
    		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.centerOnScreen();
    		stage.setScene(scene);
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
	
}
