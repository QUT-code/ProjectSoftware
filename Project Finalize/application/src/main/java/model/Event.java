package model;

import java.time.LocalDate;

import javafx.scene.Node;

public class Event {
	private String location;
	private int id;
	private int eventID;
    public int getEventID() {
		return eventID;
	}
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	private String eventName;
    private String eventCategory;
    private String contactEmail;
    private String contactname;
    private String phoneNumber;
	private String eventDate;
    private String eventTicketPrice;
    private String eventTitle;
    private String eventSubtitle;
    private String eventLayoutCover;
    private String eventDescription;
    private String eventBackground;
    public String getEventBackground() {
		return eventBackground;
	}
	public void setEventBackground(String eventBackground) {
		this.eventBackground = eventBackground;
	}
	public String getEventCategory() {
		return eventCategory;
	}
	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEmail() {
		return contactEmail;
	}
	public void setEmail(String email) {
		this.contactEmail = email;
	}
	public String getContactname() {
		return contactname;
	}
	public void setContactname(String contactname) {
		this.contactname = contactname;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getEventTicketPrice() {
		return eventTicketPrice;
	}
	public void setEventTicketPrice(String eventTicketPrice) {
		this.eventTicketPrice = eventTicketPrice;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventSubtitle() {
		return eventSubtitle;
	}
	public void setEventSubtitle(String eventSubtitle) {
		this.eventSubtitle = eventSubtitle;
	}
	public String getEventLayoutCover() {
		return eventLayoutCover;
	}
	public void setEventLayoutCover(String eventLayoutCover) {
		this.eventLayoutCover = eventLayoutCover;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Node getSource() {
		// TODO Auto-generated method stub
		return null;
	}
}
