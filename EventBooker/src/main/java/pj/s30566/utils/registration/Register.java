package pj.s30566.utils.registration;
import pj.s30566.model.User;
import pj.s30566.utils.PasswordUtils;
import pj.s30566.utils.mysql.UserDriver;
import pj.s30566.utils.output.Wipe;

import java.io.Console;
import java.util.Scanner;

public class Register {
    UserDriver driver = new UserDriver();

    public void addUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwe uzytkownika");
        String username = scanner.nextLine();
        String password = PasswordUtils.getPassword();
        System.out.println("Podaj adres email: ");
        String email = scanner.nextLine();
        System.out.println("Jak masz na imie? ");
        String name = scanner.nextLine();
        System.out.println("A na nazwisko?");
        String surname = scanner.nextLine();
        String phone;
        int numberLength;
        do {
            System.out.println("Podaj numer telefonu");
            phone = scanner.nextLine();
            numberLength = phone.length();
        } while (numberLength != 9);


        Wipe.wipe();

        User user = new User(username, password, email, name, surname, phone);
        if (registerUser(user) == 0){
            System.out.println("Pomyslnie utworzono uzytkownika.");
        } else {
            System.out.println("Wystapił blad. Nie dodano uzytkownika.");
        }
    }

    public int registerUser(User user){
        if (isLoginUnique(user) == 0) {
            driver.insertUser(user);
            return 0;
        } else if (isLoginUnique(user) == 1){
            System.out.println("Taki uzytkownik juz istnieje!");
            return 1;
        } else {
            System.out.println("Wystapil nieznany problem podczas sprawdzania nazwy uzytkownika");
            return 1;
        }
    }

    private int isLoginUnique(User user){
        if (driver.loginCount(user) == 0){
            return 0;
        } else if (driver.loginCount(user) > 0) {
            return 1;
        } else {
            return 2;
        }
    }
}
