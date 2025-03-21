package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.ConnectEventData;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Session;
import utility.AlertUtil;

public class ControllerLogin implements Initializable{
	
	private Scene scene;
	
	private FontAwesomeIconView eyeslash = new FontAwesomeIconView(FontAwesomeIcon.EYE_SLASH);
	private FontAwesomeIconView eye = new FontAwesomeIconView(FontAwesomeIcon.EYE);
	
	@FXML
	private MediaView mediaView;
	
	private Media media;
	private File file;
	private MediaPlayer mediaPlayer;
	
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
            AlertUtil.showAlert("Error", "All fields are required!");
            return;
        }
        
        try (Connection conn = ConnectEventData.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE name = ? AND password = ?")) {
            
            stmt.setString(1, name);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                // Fetch user_id and store it in the session
                int userId = rs.getInt("user_id");
                Session.setUserId(userId);  // Store the user ID in the session
                
                AlertUtil.showAlert("Success", "Login Successful");
                loginToMain(event);
            } else {
                AlertUtil.showAlert("Failed to login", "Invalid username or password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.showAlert("Error", "Login Failed");
        }
    }

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		file = new File("sky.mp4");

        // Create a Media object for the video
        media = new Media(file.toURI().toString());

        // Create a MediaPlayer and associate it with the MediaView
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        // Set the MediaPlayer to the MediaView
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (media.getDuration().subtract(Duration.millis(100)).lessThanOrEqualTo(newTime)) {
                mediaPlayer.seek(Duration.ZERO); // Restart just before it ends
            }
        });
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
