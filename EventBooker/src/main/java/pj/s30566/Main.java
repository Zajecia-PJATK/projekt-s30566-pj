package pj.s30566;
import pj.s30566.registration.Register;
import pj.s30566.user.User;

import java.io.Console;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Register register = new Register();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwe uzytkownika");
        String username = scanner.nextLine();
        System.out.println("Podaj haslo: ");
        String password = scanner.nextLine();
        System.out.println("Podaj adres email: ");
        String email = scanner.nextLine();
        System.out.println("Jak masz na imie? ");
        String name = scanner.nextLine();
        System.out.println("A na nazwisko?");
        String surname = scanner.nextLine();
        System.out.println("Podaj numer telefonu");
        String phone = scanner.nextLine();

        User user = new User(username, password, email, name, surname, phone);
        register.registerUser(user);
    }
}