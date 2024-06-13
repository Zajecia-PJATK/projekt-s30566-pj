package pj.s30566.utils.mysql;
import pj.s30566.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class UserDriver extends MysqlDriver {
    private static final Logger logger = LoggerFactory.getLogger(UserDriver.class);
    private static final String INSERT_USERS_SQL = "INSERT INTO Users (username, password, email, name, surname, phone) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_LOGIN = "SELECT user_id, username, password, email, name, surname, phone FROM Users WHERE login = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM Users";
    private static final String GET_LOGIN_COUNT = "SELECT count(*) FROM Users WHERE username = ?";
    private static final String DELETE_USERS_SQL = "DELETE FROM Users WHERE username = ?";
    private static final String UPDATE_USERS_SQL = "UPDATE Users SET username = ?, password = ?, email = ?, name = ?, surname = ?, phone = ? WHERE user_id = ?";

    public UserDriver(){

    }

    public void insertUser(User user) {
        try (Connection connection = super.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getSurname());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("An error occurred while adding user {}", user.getLogin(), e);
        }
    }

    public int loginCount(User user) {
        try (Connection connection = super.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(GET_LOGIN_COUNT)) {
            preparedStatement.setString(1, user.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt(1);
            } else {return -1;}
        } catch (SQLException e){
            logger.error("An error occurred while counting users {}", user.getLogin(), e);
            return -1;
        }
    }
}
