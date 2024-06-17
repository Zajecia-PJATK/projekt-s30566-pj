package pj.s30566.layout;


import pj.s30566.model.Event;
import pj.s30566.model.user.User;
import pj.s30566.utils.login.Login;
import pj.s30566.utils.mysql.EventDriver;
import pj.s30566.utils.mysql.UserDriver;
import pj.s30566.utils.registration.Register;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;

public class GreetMenu implements Navigation {
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
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

    public void executeOption(int choice) {
        switch (choice) {
            case 1:
                Login login = new Login();
                int isLoggedIn = login.login();
                if (isLoggedIn == 0) {
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


    public void listEvents() {
        EventDriver eventDriver = new EventDriver();
        System.out.println("Nadchodzace wydarzenia: ");
        List<Event> eventList = eventDriver.getEvents();
        int elementCount = eventList.size();
        int index = 0;
        Console console = System.console();
        char input;
        while (true){
            System.out.println("Obiekt: " + index + "/" + elementCount + "q - wyjdź, a - poprzedni element  , d - następny element");

            System.out.println("Nazwa: " + eventList.get(index).getEventName());
            System.out.println("Typ: " + eventList.get(index).getEventName());

            if (console != null){
                try {
                    input = (char) console.reader().read();
                } catch (IOException e){
                    System.out.println("Problem z konsolą.");
                    input = scanner.next().charAt(0);
                }
                if (input == 'a'){
                    index = (index - 1 + eventList.size()) % eventList.size();
                } else if (input == 'd'){
                    index = (index + 1) % eventList.size();
                } else if (input == 'q') {
                    break;
                } else {
                    System.out.println("Zły przycisk!");
                }
            } else {
                    input = scanner.next().charAt(0);
                if (input == 'a'){
                    index = (index - 1 + eventList.size()) % eventList.size();
                } else if (input == 'd'){
                    index = (index + 1) % eventList.size();
                } else if (input == 'q') {
                    break;
                } else {
                    System.out.println("Zły przycisk!");
                }
            }



        }




    }

}
