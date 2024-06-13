package pj.s30566.registration;
import pj.s30566.model.User;
import pj.s30566.utils.mysql.UserDriver;

public class Register {
    private User user;

    private static final String INSERT_USER = "INSERT INTO Users (username, password, email, name, surname, phone) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_USERNAME = "SELECT COUNT(*) FROM Users WHERE username = ?";

    public Register(User user){
        this.user = user;
    }
    public Register(){

    }
    public User getUser(){
        return this.user;
    }

    public void registerUser(User user){
        UserDriver driver = new UserDriver();
        driver.insertUser(user);
    }
}
