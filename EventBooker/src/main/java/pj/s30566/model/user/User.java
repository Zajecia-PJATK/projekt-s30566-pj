package pj.s30566.model.user;
import pj.s30566.enums.Roles;
import pj.s30566.utils.PasswordUtils;
import pj.s30566.utils.StringUtils;

import javax.management.relation.Role;

public class User {
    private int ID;
    private String login;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String phone;
    private Roles permissionLevel;

    public User(){

    }

    public User(String login, String password, String email, String name, String surname, String phone){
        this.login = login;
        this.password = PasswordUtils.hashPassword(password);
        this.email = email;
        this.name = StringUtils.capitalizeFirstLetter(name);
        this.surname = StringUtils.capitalizeFirstLetter(surname);
        this.phone = phone;
        this.permissionLevel = Roles.CUSTOMER;
    }
    public User(String login, String password, String email, String name, String surname, String phone, Roles permissionLevel){
        this.login = login;
        this.password = PasswordUtils.hashPassword(password);
        this.email = email;
        this.name = StringUtils.capitalizeFirstLetter(name);
        this.surname = StringUtils.capitalizeFirstLetter(surname);
        this.phone = phone;
        this.permissionLevel = permissionLevel;
    }
    public User(int ID, String login, String password, String email, String name, String surname, String phone){
        this.ID = ID;
        this.login = login;
        this.password = PasswordUtils.hashPassword(password);
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.permissionLevel = Roles.CUSTOMER;
    }
    public User(int ID, String login, String password, String email, String name, String surname, String phone, Roles permissionLevel){
        this.ID = ID;
        this.login = login;
        this.password = PasswordUtils.hashPassword(password);
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.permissionLevel = permissionLevel;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Roles getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(Roles permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
