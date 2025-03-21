package model;

import java.sql.Timestamp;

public class TicketPurchase {
    private int id;
    private String eventName;
    private String ticketPrice;
    private String purchaseDate;

    public TicketPurchase(String eventName, String ticketPrice, String purchaseDate) {
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
        this.purchaseDate = purchaseDate;
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

	public String getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(String ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

}


