package pj.s30566.utils.mysql;
import pj.s30566.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pj.s30566.enums.Roles;

import java.sql.*;

public class UserDriver extends MysqlDriver {
    public User user;
    private static final Logger logger = LoggerFactory.getLogger(UserDriver.class);
    private static final String INSERT_USERS_SQL = "INSERT INTO Users (username, password, email, name, surname, phone, permission_level) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_LOGIN = "SELECT user_id, username, password, email, name, surname, phone, permission_level FROM Users WHERE username = ?";
    private static final String GET_USER_PASSWORD_BY_LOGIN = "SELECT password FROM Users WHERE username = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM Users";
    private static final String GET_LOGIN_COUNT = "SELECT count(*) FROM Users WHERE username = ?";
    private static final String DELETE_USERS_SQL = "DELETE FROM Users WHERE username = ?";
    private static final String UPDATE_USERS_SQL = "UPDATE Users SET username = ?, password = ?, email = ?, name = ?, surname = ?, phone = ? WHERE user_id = ?";

    public UserDriver(){

    }

    public int insertUser(User user) {
        try (Connection connection = super.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getSurname());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setString(7, user.getPermissionLevel().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("An error occurred while adding user {}", user.getLogin(), e);
            return -1;
        }
        return 0;
    }

    public int loginCount(User user) {
        try (Connection connection = super.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(GET_LOGIN_COUNT)) {
            preparedStatement.setString(1, user.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt(1);
            } else {return 0;}
        } catch (SQLException e){
            logger.error("An error occurred while counting users {}", user.getLogin(), e);
            return -1;
        }
    }

    public String getUserPassword(String login){
        try (Connection connection = super.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_PASSWORD_BY_LOGIN)){
            preparedStatement.setString(1, login);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return result.getString(1);
            } else {
                logger.error("Nie znaleziono uzytkownika");
                return "error";
            }
        } catch (SQLException e) {
            logger.error("An error occurred while getting user password {}", login, e);
            return "error";
        }

    }

    public User getUser(String username){
        User user = null;
        try (Connection connection = super.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int userId = result.getInt("user_id");
                String password = result.getString("password");
                String email = result.getString("email");
                String name = result.getString("name");
                String surname = result.getString("surname");
                String phone = result.getString("phone");
                Roles role = Roles.valueOf(result.getString("permission_level"));
                user = new User(userId, username, password, email, name, surname, phone, role);
            } else {
                logger.error("Nie znaleziono uzytkownika");
                return null;
            }
        } catch (SQLException e) {
            logger.error("An error occurred while getting user {}", username, e);
            return null;
        }

        return user;

    }

}
