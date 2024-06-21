package pj.s30566.layout.loggedIn;

import pj.s30566.layout.ListEvents;
import pj.s30566.model.user.User;
import pj.s30566.utils.event.EventManager;
import pj.s30566.utils.output.Wipe;

import java.util.Scanner;

public class OrganiserMenu implements Navigation {
    private final Scanner scanner = new Scanner(System.in);
    public void displayMenu(User user){
        Wipe.wipe();
        System.out.println("EventBooker - Niezapomniane wspomnienia");
        System.out.println("Witaj, " + user.getName() + '!');
        System.out.println("=== Menu ===");
        System.out.println("1. Przegladaj nadchodzace wydarzenia");
        System.out.println("2. Dodaj nowe wydarzenie");
        System.out.println("0. Wyloguj sie");

        int choice = getUserChoice();
        executeOption(choice, user);


    }

    public int getUserChoice(){
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public void executeOption(int choice, User user){
        switch (choice) {
            case 1:
                final ListEvents listEvents = new ListEvents();
                listEvents.listEvents();
                break;
            case 2:
                EventManager eventManager = new EventManager();
                eventManager.createNewEvent(user);
        }

    }
}
