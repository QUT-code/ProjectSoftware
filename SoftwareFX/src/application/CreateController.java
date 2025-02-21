package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CreateController implements Initializable{
	
	@FXML
	private Button userButton;
	
	@FXML
	private Scene scene;
	
	@FXML
	private Spinner<Integer> ticketPrice;
	
	@FXML 
	private ChoiceBox<String> categoryChoice;
	
	private String[] category = {"Souvneir", "Food and Beverage", "Health and Wellness", "Accommodation", "Travel", "Clothes and Accessary"};

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		String username = getUsernameFromFile();
	    if (username.isEmpty()) {
	        userButton.setText("Guest"); 
	    } else {
	        userButton.setText(username);
	    }
	    
		categoryChoice.getItems().addAll(category);
		
		ticketPrice.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1));
		ticketPrice.valueProperty().addListener((obs, oldValue, newValue) -> {
			System.out.println("Selected Value" + newValue);
		});
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
	
	public void switchToMain(ActionEvent event) throws IOException {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
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


}
