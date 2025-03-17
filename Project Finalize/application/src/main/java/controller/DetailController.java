package controller;   
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import database.ConnectEventData;
import database.ConnectUserLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Event;
import utility.AlertUtil;
import controller.HistoryController;

public class DetailController implements Initializable {
	
	@FXML
    private Label EventTitle;

    @FXML
    private JFXButton TicketPrice;

    @FXML
    private Text dateInfo;
    
    @FXML
    private Text orgName;

    @FXML
    private TextArea descriptionField;
    
    @FXML
    private ImageView backgroundEvent;

    @FXML
    private Text locationEvent;

    public void setEventData(Event event) {
        // Set event title
        EventTitle.setText(event.getEventTitle());

        // Set event name
        orgName.setText(event.getEventName());

        // Set ticket price
        TicketPrice.setText(event.getEventTicketPrice());

        // Set event date
        dateInfo.setText(event.getEventDate());

        // Set location
        locationEvent.setText(event.getLocation());

        // Set event description
        descriptionField.setText(event.getEventDescription());
        
        if (event.getEventBackground() != null) {
            String imagePath = "src/main/resource/background/" + event.getEventBackground(); // Convert relative path
            File imageFile = new File(imagePath);

            if (imageFile.exists()) {
                Image image = new Image(imageFile.toURI().toString());
                backgroundEvent.setImage(image);
            } else {
                System.out.println("Image not found: " + imagePath);
            }
        }
        
    }

    @FXML
    public void purchaseTicket(ActionEvent event) {
        // Example IDs, replace with actual logic to retrieve userId and eventId
        int userId = getUserId();  // Implement your own method to retrieve the userId
        int eventId = getEventId();  // Implement your own method to retrieve the eventId

        // Check if user exists in the users database
        if (!isUserValid(userId)) {
            System.out.println("Invalid user!");
            AlertUtil.showAlert("Error", "Invalid user! Please try again.");
            return;
        }

        // Check if event exists in the events database
        if (!isEventValid(eventId)) {
            System.out.println("Invalid event!");
            AlertUtil.showAlert("Error", "Invalid event! Please try again.");
            return;
        }

        // Proceed to insert into ticket_purchases table
        String query = "INSERT INTO ticket_purchases (user_id, event_id) VALUES (?, ?)";
        try (Connection conn = ConnectEventData.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);  
            stmt.setInt(2, eventId);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Ticket purchased successfully!");
                AlertUtil.showAlert("Success", "Ticket purchased successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtil.showAlert("Error", "Error purchasing the ticket. Please try again.");
        }
    }

    public boolean isUserValid(int userId) {
        String query = "SELECT id FROM users WHERE id = ?";
        try (Connection conn = ConnectUserLogin.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("User is valid.");
                return true; // User is valid
            } else {
                System.out.println("User is invalid.");
                return false; // User is invalid
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if there is an error
        }
    }

    public boolean isEventValid(int eventId) {
        String query = "SELECT id FROM events WHERE id = ?";
        try (Connection conn = ConnectEventData.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, eventId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Event is valid.");
                return true; // Event is valid
            } else {
                System.out.println("Event is invalid.");
                return false; // Event is invalid
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if there is an error
        }
    }

    // Example methods to retrieve userId and eventId
    private int getUserId() {
        // Replace with your actual logic to retrieve the userId
        return 1; // Example userId (replace with actual logic)
    }

    private int getEventId() {
        // Replace with your actual logic to retrieve the eventId
        return 1; // Example eventId (replace with actual logic)
    }




	// Example method to process ticket purchase (Modify as needed)
//	private void processTicketPurchase() {
//	    // Implement ticket purchasing logic here
//	    System.out.println("Ticket purchased successfully!");
//
//	    // Show a success alert
//	    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
//	    successAlert.setTitle("Success");
//	    successAlert.setHeaderText(null);
//	    successAlert.setContentText("Your ticket has been successfully purchased!");
//	    successAlert.showAndWait();
//	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
}

	

