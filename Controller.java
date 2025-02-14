package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;


public class Controller {
	@FXML
	private ToggleButton toggleMode;
	
	@FXML
	private Image moonImage = new Image(getClass().getResourceAsStream("moon.png"));
    private ImageView moonImageView = new ImageView(moonImage);
    
    
    @FXML
    private Image sunImage = new Image(getClass().getResourceAsStream("sun.png"));
    private ImageView sunImageView = new ImageView(sunImage);
	
    @FXML
    private Pane rootPane; 

    @FXML
    private Rectangle sideBar;
    
    @FXML
    private VBox menuBar;
	
	@FXML
	    private Button closeButton;
	    private Button minimizeButton; 
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	 	private void handleCloseAction() {
	        Platform.exit(); 
	    }
	
	@FXML
	    private void handleMinimizeAction(ActionEvent event) {
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setIconified(true); 
	    }
	
	public void switchToCreate(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("CreateEvent.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	@FXML
		public void switchTheme() {
		    boolean darkMode = toggleMode.isSelected();
		    rootPane.getScene().getStylesheets().clear();
	
		    if (darkMode) {
		        rootPane.getScene().getStylesheets().add(getClass().getResource("darkmode.css").toExternalForm());
		        toggleMode.setGraphic(moonImageView);
		        moonImageView.setFitWidth(109);
		        moonImageView.setFitHeight(25);
			    moonImageView.setPreserveRatio(true);
		    } else {
		        rootPane.getScene().getStylesheets().add(getClass().getResource("lightmode.css").toExternalForm());
		        toggleMode.setGraphic(sunImageView);
		        sunImageView.setFitWidth(109);
		        sunImageView.setFitHeight(25);
			    sunImageView.setPreserveRatio(true);
		    }
		}
}
