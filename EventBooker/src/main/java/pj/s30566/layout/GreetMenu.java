package pj.s30566.layout;


import pj.s30566.model.user.User;
import pj.s30566.utils.login.Login;
import pj.s30566.utils.mysql.UserDriver;
import pj.s30566.utils.registration.Register;

import java.util.Scanner;

public class GreetMenu implements Navigation {
    private final Scanner scanner = new Scanner(System.in);
    public void displayMenu(){
        System.out.println("EventBooker - Niezapomniane wspomnienia");
        System.out.println("=== Menu ===");
        System.out.println("1. Zaloguj sie");
        System.out.println("2. Zarejestruj sie");
        System.out.println("3. Przegladaj nadchodzace wydarzenia");
        System.out.println("0. Exit");
        System.out.print("Wybierz opcje: ");
    }

    public int getUserChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public void executeOption(int choice){
        switch (choice){
            case 1:
                Login login = new Login();
                int isLoggedIn = login.login();
                if (isLoggedIn == 0){
                    UserDriver userDriver = new UserDriver();
                    User user = userDriver.getUser(login.getLogin());
                    // tutaj będzie przejscie do zalogowanego menu
                }
                break;
            case 2:
                Register register = new Register();
                register.addUser();
                break;
            case 3:

        }
    }
}
