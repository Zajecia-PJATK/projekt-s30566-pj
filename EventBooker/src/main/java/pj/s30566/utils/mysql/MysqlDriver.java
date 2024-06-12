package pj.s30566.utils.mysql;
import java.sql.*;

public class MysqlDriver {

    private String url;
    private String user;
    private String password;

    public MysqlDriver(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }
    public MysqlDriver(){
        this.url = "jdbc:mysql://localhost:3306/database";
        this.user = "username";
        this.password = "password";
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public ResultSet executeQuery(String query) throws SQLException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public int executeUpdate(String query) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }



}
