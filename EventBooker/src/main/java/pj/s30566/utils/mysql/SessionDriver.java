package pj.s30566.utils.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pj.s30566.model.Session;
import pj.s30566.utils.SystemIdentifier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionDriver extends MysqlDriver{
    private static final Logger logger = LoggerFactory.getLogger(SessionDriver.class);
    private static final String INSERT_SESSION_SQL = "INSERT INTO Sessions (system_username, app_username, system_mac, time,) VALUES (?, ?, ?, ?)";
    private static final String DELETE_OLD_SESSIONS_SQL = "DELETE FROM Sessions WHERE last_activity < NOW() - INTERVAL 1 DAY";
    private static final String SELECT_SESSION = "SELECT session_id FROM Sessions WHERE system_mac = ?";

    public void insertSession(Session session) {
        try (Connection connection = super.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SESSION_SQL)){
            preparedStatement.setString(1, session.getSystemUsername());
            preparedStatement.setString(2, session.getAppUsername());
            preparedStatement.setString(3, session.getSystemMac());
            preparedStatement.setString(4, session.getTime().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("An error occurred while adding session", e);
        }
    }

    public void deleteOldSessions() {
        try (Connection connection = super.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_OLD_SESSIONS_SQL)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("An error occured while cleaning sessions", e);
        }
    }

    public String getSession(){
        deleteOldSessions();
        try (Connection connection = super.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SESSION)){
            preparedStatement.setString(1, SystemIdentifier.getMacAddress());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.error("An error occured while cleaning sessions", e);
        }
        return null;
    }

}
