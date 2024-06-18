package pj.s30566.utils.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pj.s30566.enums.EventStatus;
import pj.s30566.enums.EventType;
import pj.s30566.enums.TicketStatus;
import pj.s30566.model.Event;
import pj.s30566.model.Ticket;
import pj.s30566.utils.FormatDT;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TicketDriver extends MysqlDriver {
    private static final Logger logger = LoggerFactory.getLogger(TicketDriver.class);
    private static final String INSERT_TICKET_SQL = "INSERT INTO Tickets (event_id, user_id, seat_number, event_name, purchase_date, status, ticket_cost) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_TICKET_SQL = "SELECT * FROM Tickets WHERE ticket_id = ?";
    private FormatDT formatter;
    public TicketDriver(){

    }

    public void addTicket(Ticket ticket) {
        try (PreparedStatement preparedStatement = super.getConnection().prepareStatement(INSERT_TICKET_SQL)){
            preparedStatement.setInt(1, ticket.getEventId());
            preparedStatement.setInt(2, ticket.getUserId());
            preparedStatement.setInt(3, ticket.getSeatNumber());
            preparedStatement.setString(4, ticket.getEventName());
            preparedStatement.setString(5, formatter.format(ticket.getPurchaseDate()));
            preparedStatement.setString(6, ticket.getStatus().name());
            preparedStatement.setDouble(7, ticket.getTicketPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("An error occurred while adding ticket", e);
        }
    }

    public Ticket getTicket(int eventId){
        int ticketId;
        int userId;
        int seatNumber;
        String eventName;
        LocalDateTime purchaseDate;
        TicketStatus status;
        double ticketPrice;
        try (Connection connection = super.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(GET_TICKET_SQL)){
            preparedStatement.setInt(1, eventId);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                ticketId = result.getInt("ticket_id");
                userId = result.getInt("user_id");
                seatNumber = result.getInt("seat_number");
                eventName = result.getString("event_name");
                purchaseDate = formatter.getLocalDateTime(result.getString("purchase_date")) ;
                status = TicketStatus.valueOf(result.getString("status"));
                ticketPrice = result.getDouble("ticket_price");

            } else {
                logger.error("Nie znaleziono biletu na wydarzenie o ID " + eventId);
                return null;
            }
        } catch (SQLException e) {
            logger.error("An error occurred while getting ticket for event {}", eventId, e);
            return null;
        }
        Ticket ticket;
        return ticket = new Ticket(ticketId, eventId, userId, seatNumber, eventName, purchaseDate, status, ticketPrice);
    }


}
