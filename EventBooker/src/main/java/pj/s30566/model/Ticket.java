package pj.s30566.model;

import pj.s30566.enums.TicketStatus;

import java.time.LocalDateTime;

public class Ticket {
    private int ticketId;
    private int eventId;
    private int userId;
    private int seatNumber;
    private String eventName;
    private LocalDateTime purchaseDate;
    private TicketStatus status;
    private double ticketPrice;

    public Ticket(int ticketId, int eventId, int userId, int seatNumber, String eventName, LocalDateTime purchaseDate, TicketStatus status, double ticketPrice){
        this.ticketId = ticketId;
        this.eventId = eventId;
        this.userId = userId;
        this.seatNumber = seatNumber;
        this.eventName = eventName;
        this.purchaseDate = purchaseDate;
        this.status = status;
        this.ticketPrice = ticketPrice;
    }
    public Ticket( int eventId, int userId, int seatNumber, String eventName, LocalDateTime purchaseDate, TicketStatus status, double ticketPrice){
        this.eventId = eventId;
        this.userId = userId;
        this.seatNumber = seatNumber;
        this.eventName = eventName;
        this.purchaseDate = purchaseDate;
        this.status = status;
        this.ticketPrice = ticketPrice;

    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", eventId=" + eventId +
                ", userId=" + userId +
                ", seatNumber='" + seatNumber + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", status='" + status + '\'' +
                '}';
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
