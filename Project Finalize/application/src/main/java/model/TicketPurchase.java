package model;

import java.sql.Timestamp;

public class TicketPurchase {
    private int id;
    private String eventName;
    private String ticketPrice;
    private String purchaseDate;

    public TicketPurchase(int id, String eventName, String ticketPrice, String purchaseDate) {
        this.id = id;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
        this.purchaseDate = purchaseDate;
    }

    public int getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }
}


