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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Event;
import model.Session;
import utility.AlertUtil;
import controller.HistoryController;

public class DetailController implements Initializable {
	
	@FXML
    private Label EventTitle;

    @FXML
    private ImageView backgroundEvent;

    @FXML
    private JFXButton dateInfo;

    @FXML
    private TextArea descriptionField;

    @FXML
    private JFXButton locationEvent;

    @FXML
    private JFXButton orgName;

    @FXML
    private JFXButton ticketPrice;

    public void setEventData(Event event) {
        // Set event title
        EventTitle.setText(event.getEventTitle());

        // Set event name
        orgName.setText(event.getEventName());

        // Set ticket price
        ticketPrice.setText(event.getEventTicketPrice());

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
        String query = "SELECT user_id FROM users WHERE user_id = ?";
        try (Connection conn = ConnectEventData.getConnection();
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
        String query = "SELECT event_id FROM events WHERE event_id = ?";
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
    	return Session.getUserId(); // Example userId (replace with actual logic)
    }

    private int getEventId() {
        // Replace with your actual logic to retrieve the eventId
        return 1; // Example eventId (replace with actual logic)
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
}

	

