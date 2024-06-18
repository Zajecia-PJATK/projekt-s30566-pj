package pj.s30566.model;

import pj.s30566.enums.EventStatus;
import pj.s30566.enums.EventType;

import java.time.LocalDateTime;

public class Event {
    private int eventId;
    private double ticketPrice;
    private int organizerId;
    private int locationId;
    private int venueId;
    private String eventName;
    private int seatNumber;
    private LocalDateTime scheduledDate;
    private EventStatus status;
    private EventType eventType;

    public Event(double ticketPrice, int organizerId, int locationId, int venueId, String eventName, int seatNumber, LocalDateTime scheduledDate, EventStatus status, EventType eventType){
        this.ticketPrice = ticketPrice;
        this.organizerId = organizerId;
        this.locationId = locationId;
        this.venueId = venueId;
        this.eventName = eventName;
        this.seatNumber = seatNumber;
        this.scheduledDate = scheduledDate;
        this.status = status;
        this.eventType = eventType;
    }

    public Event(int eventId, double ticketPrice, int organizerId, int locationId, int venueId, String eventName, int seatNumber, LocalDateTime time, EventStatus status, EventType type) {
        this.eventId = eventId;
        this.ticketPrice = ticketPrice;
        this.organizerId = organizerId;
        this.locationId = locationId;
        this.venueId = venueId;
        this.eventName = eventName;
        this.seatNumber = seatNumber;
        this.scheduledDate = time;
        this.status = status;
        this.eventType = type;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
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

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
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

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
