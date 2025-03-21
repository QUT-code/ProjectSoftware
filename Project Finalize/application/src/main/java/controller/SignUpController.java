package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Session;
import utility.AlertUtil;

public class SignUpController implements Initializable{
	
	private Scene scene;
	
	private FontAwesomeIconView eyeslash = new FontAwesomeIconView(FontAwesomeIcon.EYE_SLASH);
	private FontAwesomeIconView eye = new FontAwesomeIconView(FontAwesomeIcon.EYE);
	
	@FXML
	private AnchorPane background1;
	
	@FXML
	private AnchorPane background2;
	
	@FXML
	private MediaView mediaView;
	
	private Media media;
	private File file;
	private MediaPlayer mediaPlayer;
	
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
	     
	     try (Connection conn = ConnectEventData.getConnection();
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
	                     Session.setUserId(userId);
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
		String[] colors = {
	            "#d3cce3", "#e9e4f0", "#a8c0ff", "#3f2b96", "#ff758c", "#ff7eb3"
	        };

//		background1.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #d3cce3, #e9e4f0);");
//        background2.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #74ebd5, #ACB6E5);");
//
//        background2.setOpacity(0); // Initially transparent
//
//        // Gradient options
//        String[] gradients = {
//            "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #d3cce3, #e9e4f0);",
//            "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #74ebd5, #ACB6E5);",
//            "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #ff758c, #ff7eb3);",
//            "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #c471f5, #fa71cd);"
//        };
//
//        Random random = new Random();
//
//        // Timeline to change gradient smoothly every 5 seconds
//        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
//            // Pick a random gradient
//            String newGradient = gradients[random.nextInt(gradients.length)];
//
//            // Swap backgrounds
//            if (background1.getOpacity() == 1) {
//                background2.setStyle(newGradient);
//                fadeIn(background2, background1);
//            } else {
//                background1.setStyle(newGradient);
//                fadeIn(background1, background2);
//            }
//        }));
//
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
		
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
	
//	private void fadeIn(AnchorPane fadeInPane, AnchorPane fadeOutPane) {
//        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2.5), fadeInPane);
//        fadeIn.setFromValue(0);
//        fadeIn.setToValue(1);
//
//        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2.5), fadeOutPane);
//        fadeOut.setFromValue(1);
//        fadeOut.setToValue(0);
//
//        fadeIn.play();
//        fadeOut.play();
//    }
	
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

