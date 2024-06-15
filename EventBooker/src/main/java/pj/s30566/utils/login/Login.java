package pj.s30566.utils.login;

import pj.s30566.utils.PasswordUtils;
import pj.s30566.utils.mysql.UserDriver;

import java.util.Scanner;

public class Login {
    private final Scanner scanner = new Scanner(System.in);
    public int login(){
        String login;
        String passwd;
        int result;
        do {
            System.out.println("Podaj swoj login (pusta linia jesli chcesz wrocic): ");
            login = scanner.nextLine();
            if (login.isEmpty()){ return -1;}
            passwd = PasswordUtils.getPassword();
            result = validate(login, passwd);
            if (passwd.isEmpty()){
                return -1;
            }
        } while (result == 1);
        return 0;
    }

    public int validate(String login, String passwd){
        UserDriver userDriver = new UserDriver();
        String givenPasswd = PasswordUtils.hashPassword(passwd);
        String actualPasswd = userDriver.getUserPassword(login);
        if (givenPasswd.equals(actualPasswd)){
            System.out.println("Prawidlowe haslo");
            return 0;
        } else {
            System.out.println("Podano zle haslo!");
            return 1;
        }

    }
}
