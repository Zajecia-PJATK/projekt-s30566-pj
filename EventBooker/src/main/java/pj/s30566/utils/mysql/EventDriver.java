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
    private String ADD_EVENT_SQL = "INSERT INTO Events (location_id, organizer_id, ticket_id, venue_id, seat_number, event_name, event_date, event_status, event_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String GET_EVENT_BY_NAME = "SELECT * FROM Events WHERE event_name = ?";
    private String CHANGE_EVENT_STATUS = "UPDATE Events SET event_status = ? WHERE event_id = ?";
    private String CHANGE_EVENT_TIME = "UPDATE Events SET event_date = ? WHERE event_id = ?";
    private String SELECT_ALL_EVENTS = "SELECT * FROM Events";
    private FormatDT formatter;

    public void addEvent(Event event){
        try (PreparedStatement preparedStatement = super.getConnection().prepareStatement(ADD_EVENT_SQL)){
            preparedStatement.setInt(1, event.getLocationId());
            preparedStatement.setInt(2, event.getOrganizerId());
            preparedStatement.setInt(3, event.getTicketId());
            preparedStatement.setInt(4, event.getVenueId());
            preparedStatement.setInt(5, event.getSeatNumber());
            preparedStatement.setString(6, event.getEventName());
            preparedStatement.setString(7, formatter.format(event.getScheduledDate()));
            preparedStatement.setString(8, event.getStatus().toString());
            preparedStatement.setString(9, event.getEventType().name());
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
        int ticketId;
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
                locationId = result.getInt("location_id");
                organizerId = result.getInt("organizer_id");
                ticketId = result.getInt("ticket_id");
                venueId = result.getInt("venue_id");
                seatNumber = result.getInt("seat_number");
                time = formatter.getLocalDateTime(result.getString("event_date"));
                status = EventStatus.valueOf(result.getString("event_status"));
                type = EventType.valueOf(result.getString("event_type"));

            } else {
                logger.error("Nie znaleziono eventu " + eventName);
                return null;
            }
        } catch (SQLException e) {
            logger.error("An error occurred while getting event {}", eventName, e);
            return null;
        }
        event = new Event(eventId, ticketId, organizerId, locationId, venueId, eventName, seatNumber, time, status, type);
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
        int ticketId;
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
                locationId = result.getInt("location_id");
                organizerId = result.getInt("organizer_id");
                ticketId = result.getInt("ticket_id");
                venueId = result.getInt("venue_id");
                seatNumber = result.getInt("seat_number");
                eventName = result.getString("event_name");
                time = formatter.getLocalDateTime(result.getString("event_date"));
                status = EventStatus.valueOf(result.getString("event_status"));
                type = EventType.valueOf(result.getString("event_type"));

                Event event = new Event(eventId, ticketId, organizerId, locationId, venueId, eventName, seatNumber, time, status, type);
                events.add(event);
            }
        } catch (SQLException e) {
            logger.error("An error occurred while getting events ", e);
        }
        return events;
    }

}
