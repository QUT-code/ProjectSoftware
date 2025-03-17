package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import database.ConnectEventData;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Event;
import utility.AlertUtil;

public class CreateController implements Initializable{
	
	private Scene scene;
	
	@FXML
	private ImageView mainLogo;
	
	@FXML
	private AnchorPane step1Pane;
	
	@FXML
	private AnchorPane step2Pane;
	
	@FXML
	private AnchorPane step3Pane;
	
	@FXML
	private AnchorPane step4Pane;
	
	@FXML
	private JFXButton nextButton;
	
	@FXML
	private JFXButton previousButton;
	
	@FXML
	private JFXButton submitButton;
	
	@FXML
	private JFXComboBox<String> orgCategory;
	private String[] category = {"Accommodation", "Food and Beverage", "Consumer goods/service", "Clothing and Accessary", "Souvenirs", "Health and Wellness", "Other"};
	
	@FXML
	private TextField orgName;
	
	@FXML
    private TextField contactEmail;

    @FXML
    private TextField contactName;

    @FXML
    private TextField contactNumber;
    
    @FXML
    private TextField eventLocations;
    
    @FXML
    private DatePicker eventDates;

    @FXML
    private TextField eventPrice;

    @FXML
    private TextArea eventSubTitles;

    @FXML
    private TextField eventTitles;

    @FXML
    private ImageView layoutCover;
    
    @FXML
    private ImageView eventBackground;

    @FXML
    private TextArea eventDescriptions;
    
    @FXML
    private Text textEventDate;

    @FXML
    private Text textEventPrice;

    @FXML
    private Text textEventSubTitle;

    @FXML
    private Text textEventTitle;

    @FXML
    private Text textFullName;

    @FXML
    private Text textOrgCategory;

    @FXML
    private Text textOrgName;

    @FXML
    private Text textPhoneNumber;
    
    private int currentStep = 1;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Image logo = new Image(getClass().getResource("/images/eagle.png").toExternalForm());
		mainLogo.setImage(logo);
		updateView();
		orgCategory.getItems().addAll(category);
		orgCategory.setValue("Default");
	}
	
	 @FXML
	 private void handleNextButtonAction() {
	        if (currentStep < 4) {
	            currentStep++;
	            updateView();
	        }
	    }

	 @FXML
	 private void handlePreviousButtonAction() {
	        if (currentStep > 1) {
	            currentStep--;
	            updateView();
	        }
	    }
	 
	 private void updateView() {
	        step1Pane.setVisible(currentStep == 1);
	        step2Pane.setVisible(currentStep == 2);
	        step3Pane.setVisible(currentStep == 3);
	        step4Pane.setVisible(currentStep == 4);

	    }
	
	 @FXML
	 private void handleSubmitButtonAction() {
	     // Print out all the form fields
	     System.out.println("Organization Name: " + orgName.getText());
	     System.out.println("Contact Email: " + contactEmail.getText());
	     System.out.println("Contact Name: " + contactName.getText());
	     System.out.println("Contact Number: " + contactNumber.getText());
	     System.out.println("Event Dates: " + eventDates.getValue()); // DatePicker returns a LocalDate
	     System.out.println("Event Price: " + eventPrice.getText());
	     System.out.println("Event Location: " + eventLocations.getText());
	     System.out.println("Event Subtitles: " + eventSubTitles.getText());
	     System.out.println("Event Titles: " + eventTitles.getText());
	     System.out.println("Event Descriptions: " + eventDescriptions.getText());

	     // For ImageView, you can print the image URL or other properties
	     if (layoutCover.getImage() != null) {
	         System.out.println("Layout Cover Image: " + layoutCover.getImage().getUrl());
	     } else {
	         System.out.println("Layout Cover Image: Not set");
	     }

	     if (eventBackground.getImage() != null) {
	         System.out.println("Event Background Image: " + eventBackground.getImage().getUrl());
	     } else {
	         System.out.println("Event Background Image: Not set");
	     }

	     // Optionally, you can add a confirmation message or clear the form
	     System.out.println("Form submitted successfully!");
	 }
	 
	 public static int insertEvent(Event event) {
		    String query = "INSERT INTO events (event_name, event_category, contact_email, contact_name, phone_number, event_date, event_ticket_price, event_title, event_subtitle, event_layout_cover, event_description, location, event_background) " +
		                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		    try (Connection conn = ConnectEventData.getConnection(); 
		         PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {  // Request to return generated keys

		        // Set the parameters for the PreparedStatement
		        stmt.setString(1, event.getEventName());
		        stmt.setString(2, event.getEventCategory());
		        stmt.setString(3, event.getEmail());
		        stmt.setString(4, event.getContactname());
		        stmt.setString(5, event.getPhoneNumber());
		        stmt.setString(6, event.getEventDate());
		        stmt.setString(7, event.getEventTicketPrice());
		        stmt.setString(8, event.getEventTitle());
		        stmt.setString(9, event.getEventSubtitle());
		        stmt.setString(10, event.getEventLayoutCover());
		        stmt.setString(11, event.getEventDescription());
		        stmt.setString(12, event.getLocation());
		        stmt.setString(13, event.getEventBackground());

		        // Execute the query
		        int rowsAffected = stmt.executeUpdate();

		        if (rowsAffected > 0) {
		            // If rows are affected, retrieve the generated event_id
		            try (ResultSet rs = stmt.getGeneratedKeys()) {
		                if (rs.next()) {
		                    return rs.getInt(1);  // The event_id is the first column in the generated keys
		                }
		            }
		        }
		        return -1;  // Return -1 if insertion failed or event_id not retrieved
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return -1;  // Return -1 if an error occurs
		    }
		}

	
	 @FXML
	 private void addEvent() {
	     // Validate required fields
	     if (orgName.getText().isEmpty() || contactEmail.getText().isEmpty() || eventDates.getValue() == null) {
	         AlertUtil.showAlert("Error", "Please fill all required fields (Organization Name, Contact Email, Event Date).");
	         return;
	     }

	     // Get the data from the form fields
	     String eventName = orgName.getText();
	     String eventCategory = orgCategory.getValue();
	     String email = contactEmail.getText();
	     String name = contactName.getText();
	     String phoneNumber = contactNumber.getText();
	     LocalDate intEventDate = eventDates.getValue();
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	     String eventDate = intEventDate.format(formatter);
	     String ticketPrice = eventPrice.getText();
	     String eventTitle = eventTitles.getText();
	     String eventSubtitle = eventSubTitles.getText();
	     String eventDescription = eventDescriptions.getText();
	     String eventLocation = eventLocations.getText();

	     // Handle the image path
	     String coverImagePath = "";
	     if (layoutCover.getImage() != null) {
	         coverImagePath = layoutCover.getImage().getUrl(); // Get the URL of the image
	     }
	     
	     String bgImagePath = "";
	     if (eventBackground.getImage() != null) {
	         bgImagePath = eventBackground.getImage().getUrl(); // Get the URL of the image
	     }

	     // Create an Event object using the form data
	     Event event = new Event();
	     event.setEventName(eventName);
	     event.setEventCategory(eventCategory);
	     event.setEmail(email);
	     event.setContactname(name);
	     event.setPhoneNumber(phoneNumber);
	     event.setEventDate(eventDate);
	     event.setEventTicketPrice(ticketPrice);
	     event.setEventTitle(eventTitle);
	     event.setEventSubtitle(eventSubtitle);
	     event.setEventLayoutCover(coverImagePath);
	     event.setEventDescription(eventDescription);
	     event.setLocation(eventLocation);
	     event.setEventBackground(bgImagePath);

	     // Insert the event into the database
	     int eventId = insertEvent(event);

	     if (eventId != -1) {
	         AlertUtil.showAlert("Success", "Event has been added successfully! Event ID: " + eventId);
	         handleSubmitButtonAction();
	         clearForm(); // Clear the form after successful submission
	     } else {
	         AlertUtil.showAlert("Error", "Failed to add event. Please try again.");
	     }
	 }
	
	private void clearForm() {
	    orgName.clear();
	    contactEmail.clear();
	    contactName.clear();
	    contactNumber.clear();
	    eventDates.setValue(null);
	    eventPrice.clear();
	    eventTitles.clear();
	    eventSubTitles.clear();
	    eventDescriptions.clear();
	    layoutCover.setImage(null);
	    eventBackground.setImage(null);
	}
	
	@FXML
	private void uploadBackgroundImage() {
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Select Event Background Image");
	    fileChooser.getExtensionFilters().addAll(
	        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
	    );
	    File selectedFile = fileChooser.showOpenDialog(null);

	    if (selectedFile != null) {
	        try {
	            // Define the target folder in your project
	            String targetFolderPath = "/src/main/resource/background/";
	            File targetFolder = new File(targetFolderPath);

	            // Ensure the target folder exists
	            if (!targetFolder.exists()) {
	                targetFolder.mkdirs(); // Create the folder if it doesn't exist
	            }

	            // Define the target file path
	            String targetFilePath = targetFolderPath + selectedFile.getName();
	            File targetFile = new File(targetFilePath);

	            // Copy the selected file to the target folder
	            Files.copy(selectedFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

	            // Load the image into the ImageView
	            Image image = new Image(targetFile.toURI().toString());
	            eventBackground.setImage(image);

	            // Set the relative path in the event object
	            String relativePath = selectedFile.getName();
	            Event event = new Event();
				event.setEventLayoutCover(relativePath);

	            // Optionally, show a success message
	            AlertUtil.showAlert("Success", "Image uploaded and saved successfully!");
	        } catch (Exception e) {
	            e.printStackTrace();
	            AlertUtil.showAlert("Error", "Could not load or save the image");
	        }
	    }
	}
	
	@FXML
	private void uploadCoverImage() {
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Select Event Cover Image");
	    fileChooser.getExtensionFilters().addAll(
	        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
	    );
	    File selectedFile = fileChooser.showOpenDialog(null);

	    if (selectedFile != null) {
	        try {
	            // Define the target folder in your project
	            String targetFolderPath = "/src/main/resource/cover/";
	            File targetFolder = new File(targetFolderPath);

	            // Ensure the target folder exists
	            if (!targetFolder.exists()) {
	                targetFolder.mkdirs(); // Create the folder if it doesn't exist
	            }

	            // Define the target file path
	            String targetFilePath = targetFolderPath + selectedFile.getName();
	            File targetFile = new File(targetFilePath);

	            // Copy the selected file to the target folder
	            Files.copy(selectedFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

	            // Load the image into the ImageView
	            Image image = new Image(targetFile.toURI().toString());
	            layoutCover.setImage(image);

	            // Set the relative path in the event object
	            String relativePath = selectedFile.getName();
	            Event event = new Event();
				event.setEventLayoutCover(relativePath);

	            // Optionally, show a success message
	            AlertUtil.showAlert("Success", "Image uploaded and saved successfully!");
	        } catch (Exception e) {
	            e.printStackTrace();
	            AlertUtil.showAlert("Error", "Could not load or save the image");
	        }
	    }
	}

	
	@FXML
	public void backToEvent(ActionEvent event) throws IOException {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
    		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
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
	public void switchToHistory(ActionEvent event) throws IOException {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/fxml/History.fxml"));
    		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
		

}
