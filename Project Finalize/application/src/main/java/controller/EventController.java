package controller;

import java.io.File;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.Event;

public class EventController {
	@FXML
    private Label eventDate;

    @FXML
    private ImageView eventImage;

    @FXML
    private Label eventLocation;

    @FXML
    private Label eventName;

    @FXML
    private Label eventPrice;
    
    @FXML
    private HBox eventHolder; // Assuming you have an HBox in your Event.fxml with fx:id="eventHBox"

    private Event event;

    public void setOnClickHandler(EventHandler<MouseEvent> handler) {
        eventHolder.setOnMouseClicked(handler);
    }

    public Event getEvent() {
        return event;
    }
    
    public void setData(Event event) {
    	if (event.getEventLayoutCover() != null) {
            String imagePath = "src/main/resource/cover/" + event.getEventLayoutCover(); // Convert relative path
            File imageFile = new File(imagePath);

            if (imageFile.exists()) {
                Image image = new Image(imageFile.toURI().toString());
                eventImage.setImage(image);
            } else {
                System.out.println("Image not found: " + imagePath);
            }
        }
    	eventName.setText(event.getEventSubtitle());
    	eventPrice.setText(event.getEventTicketPrice());
    	eventLocation.setText(event.getLocation());
    	eventDate.setText(event.getEventDate());
    }
}
