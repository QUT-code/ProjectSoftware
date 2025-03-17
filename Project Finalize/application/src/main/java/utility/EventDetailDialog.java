package utility;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ScrollPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.Optional;
import model.Event;

import controller.DetailController;

public class EventDetailDialog {
	public static Optional<ButtonType> showDialog(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(EventDetailDialog.class.getResource("/fxml/EventDetails.fxml"));
            ScrollPane scrollPane = loader.load(); // Load as ScrollPane

            // Get the controller and set the event data
            DetailController controller = loader.getController();
            controller.setEventData(event);

            // Create a dialog and set the ScrollPane as its content
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.getDialogPane().setContent(scrollPane); // Set ScrollPane as content
            
            dialog.setTitle("Event Details");
            dialog.getDialogPane().getStylesheets().add(
                    EventDetailDialog.class.getResource("/css/details.css").toExternalForm()
                );

            // Add a close button
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.UNDECORATED);

            return dialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}

