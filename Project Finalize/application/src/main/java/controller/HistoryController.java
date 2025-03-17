package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import database.ConnectEventData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.TicketPurchase;
import utility.AlertUtil;

public class HistoryController {
	
	private Scene scene;
	
	@FXML
	private TableView<TicketPurchase> tableView;
	@FXML
	private TableColumn<TicketPurchase, String> eventNameColumn;
	@FXML
	private TableColumn<TicketPurchase, String> ticketPriceColumn;
	@FXML
	private TableColumn<TicketPurchase, String> purchaseDateColumn;

	@FXML
	public void initialize() {
	    eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
	    ticketPriceColumn.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));
	    purchaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));

	    loadPurchaseTickets(); // Load data when the table is initialized
	}


	@FXML
	public void loadPurchaseTickets() {
	    String query = "SELECT tp.id, e.event_name, e.event_ticket_price, tp.purchase_date " +
	                   "FROM ticket_purchases tp " +
	                   "JOIN events e ON tp.event_id = e.id";

	    ObservableList<TicketPurchase> ticketList = FXCollections.observableArrayList();

	    try (Connection conn = ConnectEventData.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String eventName = rs.getString("event_name");
	            String ticketPrice = rs.getString("event_ticket_price");
	            String purchaseDate = rs.getTimestamp("purchase_date").toString();

	            ticketList.add(new TicketPurchase(id, eventName, ticketPrice, purchaseDate));
	        }

	        tableView.setItems(ticketList);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        AlertUtil.showAlert("Error", "Failed to load ticket purchases.");
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
}
