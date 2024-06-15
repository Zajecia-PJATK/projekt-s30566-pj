package pj.s30566.model;

import pj.s30566.enums.TicketStatus;

import java.time.LocalDateTime;

public class Ticket {
    private int ticketId;
    private String eventId;
    private String userId;
    private String seatNumber;
    private LocalDateTime purchaseDate;
    private TicketStatus status;

    public Ticket(int ticketId, String eventId, String userId, String seatNumber, LocalDateTime purchaseDate, TicketStatus status){
        this.ticketId = ticketId;
        this.eventId = eventId;
        this.userId = userId;
        this.seatNumber = seatNumber;
        this.purchaseDate = purchaseDate;
        this.status = status;
    }
    public Ticket(String eventId, String userId, String seatNumber, LocalDateTime purchaseDate, TicketStatus status){
        this.eventId = eventId;
        this.userId = userId;
        this.seatNumber = seatNumber;
        this.purchaseDate = purchaseDate;
        this.status = status;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String  getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String  getUserId() {
        return userId;
    }

    public void setUserId(String  userId) {
        this.userId = userId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
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
}
