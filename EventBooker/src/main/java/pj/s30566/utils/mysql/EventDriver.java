package pj.s30566.utils.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pj.s30566.enums.EventStatus;
import pj.s30566.enums.EventType;
import pj.s30566.model.Event;
import pj.s30566.utils.FormatDT;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventDriver extends MysqlDriver {
    private static final Logger logger = LoggerFactory.getLogger(EventDriver.class);
    private String ADD_EVENT_SQL = "INSERT INTO Events (event_name, event_type, location_id, organizer_id, scheduled_date, seat_number, status, ticket_price, venue_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String GET_EVENT_BY_NAME = "SELECT * FROM Events WHERE event_name = ?";
    private String CHANGE_EVENT_STATUS = "UPDATE Events SET status = ? WHERE event_id = ?";
    private String CHANGE_EVENT_TIME = "UPDATE Events SET event_date = ? WHERE event_id = ?";
    private String SELECT_ALL_EVENTS = "SELECT * FROM Events";
    private FormatDT formatter;

    public void addEvent(Event event){
        try (PreparedStatement preparedStatement = super.getConnection().prepareStatement(ADD_EVENT_SQL)){
            preparedStatement.setString(1, event.getEventName());
            preparedStatement.setString(2, event.getEventType().name());
            preparedStatement.setInt(3, event.getLocationId());
            preparedStatement.setInt(4, event.getOrganizerId());
            preparedStatement.setString(5, formatter.format(event.getScheduledDate()));
            preparedStatement.setInt(6, event.getSeatNumber());
            preparedStatement.setString(7, event.getStatus().name());
            preparedStatement.setDouble(8, event.getTicketPrice());
            preparedStatement.setInt(9, event.getVenueId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("An error occurred while adding location {}", event.getEventName(), e);
        }
    }

    public Event getEvent(String eventName){
        Event event;
        int eventId;
        int locationId;
        int organizerId;
        double ticketPrice;
        int venueId;
        int seatNumber;
        LocalDateTime time;
        EventStatus status;
        EventType type;
        try (Connection connection = super.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(GET_EVENT_BY_NAME)){
            preparedStatement.setString(1, eventName);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                eventId = result.getInt("event_id");
                type = EventType.valueOf(result.getString("event_type"));
                locationId = result.getInt("location_id");
                organizerId = result.getInt("organizer_id");
                time = formatter.getLocalDateTime(result.getString("scheduled_date"));
                seatNumber = result.getInt("seat_number");
                status = EventStatus.valueOf(result.getString("status"));
                ticketPrice = result.getDouble("ticket_price");
                venueId = result.getInt("venue_id");
            } else {
                logger.error("Nie znaleziono eventu " + eventName);
                return null;
            }
        } catch (SQLException e) {
            logger.error("An error occurred while getting event {}", eventName, e);
            return null;
        }
        event = new Event(eventId, ticketPrice, organizerId, locationId, venueId, eventName, seatNumber, time, status, type);
        return event;

    }

    public void changeEventStatus(EventStatus status, Event event){
        try (PreparedStatement preparedStatement = super.getConnection().prepareStatement(CHANGE_EVENT_STATUS)){
            preparedStatement.setString(1, status.name());
            preparedStatement.setInt(2, event.getEventId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("An error occurred while changing event status {}", event.getEventName(), e);
        }

        System.out.println("Zmieniono status eventu na " + status);

    }

    public void changeEventTime(LocalDateTime time, Event event){
        try (PreparedStatement preparedStatement = super.getConnection().prepareStatement(CHANGE_EVENT_TIME)){
            preparedStatement.setString(1, formatter.format(time));
            preparedStatement.setInt(2, event.getEventId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("An error occurred while changing event date {}", event.getEventName(), e);
        }

        System.out.println("Zmieniono czas eventu na " + formatter.format(time));

    }

    public List<Event> getEvents() {
        int eventId;
        int locationId;
        int organizerId;
        double ticketPrice;
        int venueId;
        int seatNumber;
        String eventName;
        LocalDateTime time;
        EventStatus status;
        EventType type;
        ResultSet result;
        List<Event> events = new ArrayList<>();
        try (PreparedStatement preparedStatement = super.getConnection().prepareStatement(SELECT_ALL_EVENTS)){
            result = preparedStatement.executeQuery();
            while (result.next()){
                eventId = result.getInt("event_id");
                eventName = result.getString("event_name");
                type = EventType.valueOf(result.getString("event_type"));
                locationId = result.getInt("location_id");
                organizerId = result.getInt("organizer_id");
                time = formatter.getLocalDateTime(result.getString("scheduled_date"));
                seatNumber = result.getInt("seat_number");
                status = EventStatus.valueOf(result.getString("status"));
                ticketPrice = result.getDouble("ticket_price");
                venueId = result.getInt("venue_id");

                Event event = new Event(eventId, ticketPrice, organizerId, locationId, venueId, eventName, seatNumber, time, status, type);
                events.add(event);
            }
        } catch (SQLException e) {
            logger.error("An error occurred while getting events ", e);
        }
        return events;
    }

}
