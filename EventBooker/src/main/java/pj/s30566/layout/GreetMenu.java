package pj.s30566.layout;


import pj.s30566.enums.Roles;
import pj.s30566.layout.loggedIn.AdminMenu;
import pj.s30566.layout.loggedIn.CustomerMenu;
import pj.s30566.layout.loggedIn.Navigation;
import pj.s30566.layout.loggedIn.OrganiserMenu;
import pj.s30566.model.user.User;
import pj.s30566.utils.login.Login;
import pj.s30566.utils.mysql.UserDriver;
import pj.s30566.utils.registration.Register;

import java.util.Scanner;

public class GreetMenu {
    private final Scanner scanner = new Scanner(System.in);


    public void displayMenu() {
        System.out.println("EventBooker - Niezapomniane wspomnienia");
        System.out.println("=== Menu ===");
        System.out.println("1. Zaloguj sie");
        System.out.println("2. Zarejestruj sie");
        System.out.println("3. Przegladaj nadchodzace wydarzenia");
        System.out.println("0. Wyjdz");
        System.out.print("Wybierz opcje: ");
    }

    public int getUserChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public void executeOption(int choice) {
        switch (choice) {
            case 1:
                Login login = new Login();
                Navigation loggedInUserMenu;
                int isLoggedIn = login.login();
                if (isLoggedIn == 0) {
                    UserDriver userDriver = new UserDriver();
                    User user = userDriver.getUser(login.getLogin());
                    if (user.getPermissionLevel() == Roles.CUSTOMER){
                        loggedInUserMenu = new CustomerMenu();
                        loggedInUserMenu.displayMenu(user);

                    } else if (user.getPermissionLevel() == Roles.ORGANISER) {
                        loggedInUserMenu = new OrganiserMenu();
                        loggedInUserMenu.displayMenu(user);



                    } else if (user.getPermissionLevel() == Roles.ADMIN) {
                        loggedInUserMenu = new AdminMenu();
                        loggedInUserMenu.displayMenu(user);

                    }
                }
                break;
            case 2:
                Register register = new Register();
                register.addUser();
                break;
            case 3:
                final ListEvents listEvents = new ListEvents();
                listEvents.listEvents();

        }
    }




}
