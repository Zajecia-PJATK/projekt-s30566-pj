package pj.s30566.utils.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pj.s30566.model.Ticket;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TicketDriver extends MysqlDriver {
    private static final Logger logger = LoggerFactory.getLogger(TicketDriver.class);
    private static String INSERT_TICKET_SQL = "INSERT INTO Tickets (event_id, user_id, seat_number, purchase_date, status) VALUES (?, ?, ?, ?, ?)";
    public TicketDriver(){

    }

    public void addTicket(Ticket ticket) throws SQLException {
        try (PreparedStatement preparedStatement = super.getConnection().prepareStatement(INSERT_TICKET_SQL)){
            preparedStatement.setString(1, ticket.getEventId());
            preparedStatement.setString(2, ticket.getUserId());
            preparedStatement.setString(3, ticket.getSeatNumber());
            preparedStatement.setString(4, ticket.getPurchaseDate().toString());
            preparedStatement.setString(5, ticket.getStatus().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("An error occurred while adding ticket", e);
        }
    }



}
