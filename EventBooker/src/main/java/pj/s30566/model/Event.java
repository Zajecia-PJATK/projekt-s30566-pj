package pj.s30566.model;

import pj.s30566.enums.EventStatus;

import java.time.LocalDateTime;

public class Event {
    private int eventId;
    private int ticketId;
    private int userId;
    private int locationId;
    private int venueId;
    private String seatNumber;
    private LocalDateTime scheduledDate;
    private EventStatus status;

    public Event(int eventId, int ticketId, int userId, int locationId, int venueId, String seatNumber, LocalDateTime scheduledDate, EventStatus status){
        this.eventId = eventId;
        this.ticketId = ticketId;
        this.userId = userId;
        this.locationId = locationId;
        this.venueId = venueId;
        this.seatNumber = seatNumber;
        this.scheduledDate = scheduledDate;
        this.status = status;
    }
    public Event(int ticketId, int userId, int locationId, int venueId, String seatNumber, LocalDateTime scheduledDate, EventStatus status){
        this.ticketId = ticketId;
        this.userId = userId;
        this.locationId = locationId;
        this.venueId = venueId;
        this.seatNumber = seatNumber;
        this.scheduledDate = scheduledDate;
        this.status = status;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }
}
