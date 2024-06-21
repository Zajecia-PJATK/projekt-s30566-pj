package pj.s30566.utils.event;

import pj.s30566.enums.EventStatus;
import pj.s30566.enums.EventType;
import pj.s30566.layout.ListLoactions;
import pj.s30566.model.Event;
import pj.s30566.model.Location;
import pj.s30566.model.user.User;
import pj.s30566.utils.location.LocationManager;
import pj.s30566.utils.mysql.EventDriver;
import pj.s30566.utils.mysql.LocationDriver;
import pj.s30566.utils.output.Wipe;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class EventManager {
    Scanner scanner = new Scanner(System.in);
    ListLoactions listLoactions = new ListLoactions();
    EventDriver eventDriver = new EventDriver();

    public void createNewEvent(User user){
        Wipe.wipe();
        LocationDriver locationDriver = new LocationDriver();
        int locationsCount = locationDriver.getLocationsByUser(user.getID()).size();
        if (locationsCount == 0){
            System.out.println("Aby dodac wydarzenie musisz miec conajmniej jedna lokacje!");
            LocationManager locationManager = new LocationManager();
            locationManager.addNewLocation(user);
        }
        System.out.println("=== Nowe Wydarzenie ===");
        System.out.println(/*pusta linia*/);
        System.out.println("Podaj nazwę wydarzenia: ");
        String eventName = scanner.nextLine();
        System.out.println("W jakiej lokacji odbywa sie wydarzenie?");
        Location location = this.listLoactions.listLocations(user);
        System.out.println("W jakiej sali odbywa sie wydarzenie?");
        Location.Venue venue = this.listLoactions.selectVenue(location);
        System.out.println("Podaj cene biletu: ");
        double price = Double.parseDouble(scanner.nextLine());
        BigDecimal bd = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);
        double actualPrice = bd.doubleValue();
        System.out.println("Podaj liczbe miejsc (0 jeśli liczba miejsc rowna sie pojemnosci sali: ");
        int capacity = scanner.nextInt();
        if (capacity == 0){
            capacity = venue.getCapacity();
        }
        System.out.println("Kiedy sie odbedzie?");
        System.out.println("Podaj czas w nastepujacym formacie: YYYY-MM-DD hh:mm:ss");
        scanner.nextLine(); //bez tego nie zapyta o date
        String timeString = scanner.nextLine();
        LocalDateTime time = LocalDateTime.parse(timeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("Podaj typ wydarzenia: ");
        EventType eventType = selectEventType();
        EventStatus eventStatus = EventStatus.SCHEDULED;

        Event event = new Event(actualPrice, user.getID(), location.getLocationID(), venue.getVenueId(), eventName, capacity, time, eventStatus, eventType);

        eventDriver.addEvent(event);

    }

    public EventType selectEventType() {
        boolean isChosen = false;
        EventType chosenEvent;
        do {
            EventType[] eventTypes = EventType.values();
            int columns = 3;

            for (int i = 0; i < eventTypes.length; i++) {
                System.out.print("[" + i + "]" + eventTypes[i] + "\t\t");
                if ((i + 1) % columns == 0) {
                    System.out.println();
                }
            }

            if (eventTypes.length % columns != 0) {
                System.out.println();
            }

            System.out.println("Wybierz kraj, podajac jego numer: ");
            int choice = scanner.nextInt();


            if (choice >= 0 && choice < eventTypes.length) {
                chosenEvent = eventTypes[choice];
                isChosen = true;
            } else {
                System.out.println("Zly numer!");
                chosenEvent = null;
            }
        } while (!isChosen);



        return chosenEvent;
    }

}
