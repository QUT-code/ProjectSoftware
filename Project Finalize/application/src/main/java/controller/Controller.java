package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import database.ConnectEventData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Event;
import utility.AlertUtil;
import utility.EventDetailDialog;



public class Controller implements Initializable{
	
	private Scene scene;
	
	@FXML
	private ImageView mainLogo;
	
	@FXML
	private VBox eventPane;
	
	@FXML
    private HBox AccommodationEventContainer;

    @FXML
    private HBox ClothingEventContainer;

    @FXML
    private HBox ConsumerEventContainer;

    @FXML
    private HBox FoodBeverageEventContainer;

    @FXML
    private HBox HealthEventContainer;

    @FXML
    private HBox OtherEventContainer;

    @FXML
    private HBox SouvenirsEventContainer;
    
    List<Event> AccommodationEvent;
    List<Event> ClothingEvent;
    List<Event> ConsumerEvent;
    List<Event> FoodBeverageEvent;
    List<Event> HealthEvent;
    List<Event> SouvenirsEvent;
    List<Event> OtherEvent;
	
	@Override
	public void initialize(URL location, ResourceBundle resource) {
		
		//Input Logo
		Image logo = new Image(getClass().getResource("/images/eagle.png").toExternalForm());
		mainLogo.setImage(logo);
		

		
		//Event Added
		AccommodationEvent = new ArrayList<>(getAccommodationEvent());
		ClothingEvent = new ArrayList<>(getClothingEvent());
		ConsumerEvent = new ArrayList<>(getConsumerEvent());
		FoodBeverageEvent = new ArrayList<>(getFoodBeverageEvent());
		HealthEvent = new ArrayList<>(getHealthEvent());
		SouvenirsEvent = new ArrayList<>(getSouvenirsEvent());
		OtherEvent = new ArrayList<>(getOtherEvent());
		try {
			for (Event event : AccommodationEvent) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/Event.fxml"));
				
				HBox hBox = fxmlLoader.load();
				EventController eventController = fxmlLoader.getController();
				eventController.setData(event);
				
				eventController.setOnClickHandler(e -> {
				    Optional<ButtonType> result = EventDetailDialog.showDialog(event);

				    result.ifPresent(buttonType -> {
				        if (buttonType == ButtonType.CLOSE) {
//				            System.out.println("Dialog closed");
				        }
				    });
				});

				AccommodationEventContainer.getChildren().add(hBox);
			}
				
			for (Event event : ClothingEvent) {
		            FXMLLoader fxmlLoader = new FXMLLoader();
		            fxmlLoader.setLocation(getClass().getResource("/fxml/Event.fxml"));

		            HBox hBox = fxmlLoader.load();
		            EventController eventController = fxmlLoader.getController();
		            eventController.setData(event);

		           ClothingEventContainer.getChildren().add(hBox);
		        }
			
			for (Event event : ConsumerEvent) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/Event.fxml"));
				
				HBox hBox = fxmlLoader.load();
				EventController eventController = fxmlLoader.getController();
				eventController.setData(event);
				
				eventController.setOnClickHandler(e -> {
				    Optional<ButtonType> result = EventDetailDialog.showDialog(event);

				    result.ifPresent(buttonType -> {
				        if (buttonType == ButtonType.CLOSE) {
//				            System.out.println("Dialog closed");
				        }
				    });
				});

				ConsumerEventContainer.getChildren().add(hBox);
	        }
			
			for (Event event : FoodBeverageEvent) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/Event.fxml"));
				
				HBox hBox = fxmlLoader.load();
				EventController eventController = fxmlLoader.getController();
				eventController.setData(event);
				
				FoodBeverageEventContainer.getChildren().add(hBox);
			}
			
			for (Event event : HealthEvent) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/Event.fxml"));
				
				HBox hBox = fxmlLoader.load();
				EventController eventController = fxmlLoader.getController();
				eventController.setData(event);
				
				HealthEventContainer.getChildren().add(hBox);
			}
			
			for (Event event : SouvenirsEvent) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/Event.fxml"));
				
				HBox hBox = fxmlLoader.load();
				EventController eventController = fxmlLoader.getController();
				eventController.setData(event);
				
				SouvenirsEventContainer.getChildren().add(hBox);
			}
			
			for (Event event : OtherEvent) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/Event.fxml"));
				
				HBox hBox = fxmlLoader.load();
				EventController eventController = fxmlLoader.getController();
				eventController.setData(event);
				
				eventController.setOnClickHandler(e -> {
				    Optional<ButtonType> result = EventDetailDialog.showDialog(event);

				    result.ifPresent(buttonType -> {
				        if (buttonType == ButtonType.CLOSE) {
//				            System.out.println("Dialog closed");
				        }
				    });
				});
				
				OtherEventContainer.getChildren().add(hBox);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	@FXML
	private TextField searchField;
	
	@FXML
    private ListView<String> suggestionList;
	
	  @FXML
	    void searchBox(ActionEvent event) throws IOException {
	        String input = searchField.getText();
	        if (!input.isEmpty()) {
	            List<String> suggestions = getSuggestion(input);
	            ObservableList<String> observableSuggestions = FXCollections.observableArrayList(suggestions);
	            suggestionList.setItems(observableSuggestions);
	            suggestionList.setVisible(true);
	        } else {
	            suggestionList.setVisible(false);
	        }
	    }

	    private List<String> getSuggestion(String input) {
	        List<String> suggestions = new ArrayList<>();
	        String query = "SELECT event_name FROM events WHERE event_name LIKE ? LIMIT 5";

	        try (Connection conn = ConnectEventData.getConnection(); 
	                PreparedStatement stmt = conn.prepareStatement(query)) {

	               stmt.setString(1, input + "%");
	               ResultSet rs = stmt.executeQuery();

	               while (rs.next()) {
	                   suggestions.add(rs.getString("event_name"));
	               }
	           } catch (SQLException e) {
	               e.printStackTrace();
	           }
	           return suggestions;
	       }
	    
	    private List<Event> getEventsByCategory(String category) {
	        List<Event> events = new ArrayList<>();
	        String query = "SELECT event_name, event_subtitle, event_ticket_price, location, event_date, event_layout_cover, event_title, event_description, event_background FROM events WHERE event_category = ?";

	        try (Connection conn = ConnectEventData.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setString(1, category);
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                Event event = new Event();
	                event.setEventName(rs.getString("event_name"));
	                event.setEventSubtitle(rs.getString("event_subtitle"));
	                event.setEventTicketPrice(rs.getString("event_ticket_price") + " $");
	                event.setLocation(rs.getString("location"));
	                event.setEventDate(rs.getString("event_date"));
	                event.setEventLayoutCover(rs.getString("event_layout_cover"));
	                event.setEventTitle(rs.getString("event_title")); // Add this line
	                event.setEventDescription(rs.getString("event_description")); // Add this line
	                event.setEventBackground(rs.getString("event_background")); // Add this line
	                events.add(event);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return events;
	    }
	
	    private List<Event> getAccommodationEvent() {
	        return getEventsByCategory("Accommodation");
	    }

	    private List<Event> getClothingEvent() {
	        return getEventsByCategory("Clothing and Accessory");
	    }

	    private List<Event> getConsumerEvent() {
	        return getEventsByCategory("Consumer goods/service");
	    }

	    private List<Event> getFoodBeverageEvent() {
	        return getEventsByCategory("Food and Beverage");
	    }

	    private List<Event> getHealthEvent() {
	        return getEventsByCategory("Health and Wellness");
	    }

	    private List<Event> getSouvenirsEvent() {
	        return getEventsByCategory("Souvenirs");
	    }

	    private List<Event> getOtherEvent() {
	        return getEventsByCategory("Other");
	    }

	
		@FXML
		public void backToLogin(ActionEvent event) throws IOException {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		    alert.setTitle("Confirmation");
		    alert.setHeaderText("Are you sure?");
		    alert.setContentText("Click 'OK' to go back to Login.");

		    // Add OK and Cancel buttons
		    ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
		    ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
		    alert.getButtonTypes().setAll(okButton, cancelButton);

		    // Show the alert and wait for user response
		    Optional<ButtonType> result = alert.showAndWait();

		    if (result.isPresent() && result.get() == okButton) {
		        try {
		            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
		            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		            Scene scene = new Scene(root);
		            stage.setScene(scene);
		            stage.show();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
	    }
	
	@FXML
	public void goToCreate(ActionEvent event) throws IOException {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/fxml/Create.fxml"));
    		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
	
	@FXML
	public void switchToHistory(ActionEvent event) throws IOException {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/fxml/History.fxml"));
    		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		 String css = this.getClass().getResource("/css/details.css").toExternalForm();
    	        scene.getStylesheets().add(css);
    		stage.setScene(scene);
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
	
}
