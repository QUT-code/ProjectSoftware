package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Controller implements Initializable{
	
	@FXML
	private Button userButton;
	
	@FXML
    private Button createButton;

    @FXML
    private Button eventButton;

    @FXML
    private Button favoriteButton;

    @FXML
    private Button historyButton;
    
    @FXML
    private Pane centerScreen;
    
    private Scene scene;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String username = getUsernameFromFile();
	    if (username.isEmpty()) {
	        userButton.setText("Guest"); 
	    } else {
	        userButton.setText(username);
	    }
	}
	
	private String getUsernameFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line = reader.readLine(); 
            if (line != null) {
                String[] userData = line.split(","); 
                if (userData.length > 0) {
                    return userData[0]; 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ""; 
    }
	
	public void switchToFavorite(ActionEvent event) throws IOException {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("FavoriteScreen.fxml"));
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public void switchToHistory(ActionEvent event) throws IOException {
    	try {
	        Parent root = FXMLLoader.load(getClass().getResource("HistoryScreen.fxml"));
	        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	        scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
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
