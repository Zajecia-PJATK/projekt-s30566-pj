package pj.s30566.layout.loggedIn;

import pj.s30566.layout.ListEvents;
import pj.s30566.layout.ListLoactions;
import pj.s30566.model.Location;
import pj.s30566.model.user.User;
import pj.s30566.utils.event.EventManager;
import pj.s30566.utils.location.LocationManager;
import pj.s30566.utils.output.Wipe;

import java.util.Scanner;

public class AdminMenu implements Navigation {
    private final LocationManager locationManager = new LocationManager();
    private final Scanner scanner = new Scanner(System.in);
    public void displayMenu(User user){
        Wipe.wipe();
        System.out.println("EventBooker - Niezapomniane wspomnienia");
        System.out.println("Witaj, " + user.getName() + '!');
        System.out.println("=== Menu ===");
        System.out.println("1. Przegladaj nadchodzace wydarzenia");
        System.out.println("2. Dodaj nowe wydarzenie");
        System.out.println("3. Utworz nowa lokacje");
        System.out.println("4. Dodaj sale do lokacji");
        System.out.println("0. Wyloguj sie");

        int choice = getUserChoice();
        executeOption(choice, user);


    }

    public int getUserChoice(){
        int choice = scanner.nextInt();
        return choice;
    }

    public void executeOption(int choice, User user){
        switch (choice) {
            case 0:
                return;
            case 1:
                final ListEvents listEvents = new ListEvents();
                listEvents.listEvents();
                Wipe.wipe();
                displayMenu(user);
            case 2:
                EventManager eventManager = new EventManager();
                eventManager.createNewEvent(user);
                Wipe.wipe();
                displayMenu(user);
            case 3:
                locationManager.addNewLocation(user);
                Wipe.wipe();
                displayMenu(user);
            case 4:
                ListLoactions listLoactions = new ListLoactions();
                Location location = listLoactions.listLocations(user);
                locationManager.addNewVenue(location, 0);
                Wipe.wipe();
                displayMenu(user);

        }


    }
}
