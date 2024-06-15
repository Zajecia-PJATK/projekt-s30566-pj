package pj.s30566.utils.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pj.s30566.model.Event;
import pj.s30566.model.Location;
import pj.s30566.utils.FormatDT;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class EventDriver extends MysqlDriver {
    private static final Logger logger = LoggerFactory.getLogger(EventDriver.class);
    private String ADD_EVENT_SQL = "INSERT INTO Events (location_id, organizer_id, ticket_id, venue_id, seat_number, event_name, event_date, event_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private FormatDT format;

    public void addEvent(Event event) throws SQLException {
        try (PreparedStatement preparedStatement = super.getConnection().prepareStatement(ADD_EVENT_SQL)){
            preparedStatement.setInt(1, event.getLocationId());
            preparedStatement.setInt(2, event.getOrganizerId());
            preparedStatement.setInt(3, event.getTicketId());
            preparedStatement.setInt(4, event.getVenueId());
            preparedStatement.setInt(5, event.getSeatNumber());
            preparedStatement.setString(6, event.getEventName());
            preparedStatement.setString(7, format.format(event.getScheduledDate()));
            preparedStatement.setString(8, event.getStatus().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("An error occurred while adding location {}", event.getEventName(), e);
        }
    }

}
