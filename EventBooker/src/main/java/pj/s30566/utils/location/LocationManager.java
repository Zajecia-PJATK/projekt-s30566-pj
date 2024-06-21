package pj.s30566.utils.location;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pj.s30566.enums.Countries;
import pj.s30566.model.Location;
import pj.s30566.model.user.User;
import pj.s30566.utils.mysql.LocationDriver;
import pj.s30566.utils.output.Wipe;

import java.sql.SQLException;
import java.util.Scanner;

public class LocationManager {
    private final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(LocationManager.class);

    public Location addNewLocation(User user) {
        Wipe.wipe();
        System.out.println("=== Nowa Lokacja ===");
        System.out.println();
        System.out.println("Podaj nazwe lokacji: ");
        String locationName = scanner.nextLine();
        Countries country = selectCountry(locationName);
        Wipe.wipe();
        System.out.println("Wybrano kraj: " + country.name());
        System.out.println("Podaj nazwe miasta: ");
        scanner.nextLine(); //bez tego pomijane jest pobieranie nazwy miasta
        String city = scanner.nextLine();
        System.out.println("Podaj adres (Ulica, numer budynku/mieszkania): ");
        String address = scanner.nextLine();
        System.out.println("Podaj kod pocztowy: ");
        String postalCode = scanner.nextLine();


        Location location = new Location(locationName, city, address, postalCode, country, user.getID());
        LocationDriver locationDriver = new LocationDriver();
        Location finalLocation = locationDriver.getLocationByName(locationName);
        if (finalLocation != null){
            System.out.println("Taka lokacja juz istnieje");
            return null;
        }
        try {
            locationDriver.addLocation(location);
        } catch (SQLException e){
            logger.error("An error occurred while adding location {}", location.getLocationName(), e);
            return null;
        }

        finalLocation = locationDriver.getLocationByName(locationName);
        addNewVenue(finalLocation, 1);
        return finalLocation;
    }


    public Countries selectCountry(String locationName) {
        boolean isChosen = false;
        Countries chosenCountry;
        do {
            Wipe.wipe();
            Countries[] countries = Countries.values();
            int columns = 3;

            System.out.println("W jakim kraju znajduje sie " + locationName + '?');
            for (int i = 0; i < countries.length; i++) {
                System.out.print("[" + i + "]" + countries[i] + "\t\t");
                if ((i + 1) % columns == 0) {
                    System.out.println();
                }
            }

            if (countries.length % columns != 0) {
                System.out.println();
            }

            System.out.println("Wybierz kraj, podajac jego numer: ");
            int choice = scanner.nextInt();


            if (choice >= 0 && choice < countries.length) {
                chosenCountry = countries[choice];
                isChosen = true;
            } else {
                System.out.println("Zly numer!");
                chosenCountry = null;
            }
        } while (!isChosen);



        return chosenCountry;
    }

    public void addNewVenue(Location location, int isFromAddNewLocation){
        Wipe.wipe();
        if (isFromAddNewLocation == 1) {
            System.out.println("=== Twoja lokacja wymaga przynajmniej 1 sali ===");
        }
        System.out.println("Podaj nazwe sali:");
        String venueName = scanner.nextLine();
        System.out.println("Podaj pojemnosc sali: ");
        int capacity = scanner.nextInt();

        Location.Venue venue = new Location.Venue(location.getLocationID(), venueName, capacity);

        LocationDriver locationDriver = new LocationDriver();
        try {
            locationDriver.addVenue(venue);
        } catch (SQLException e){
            logger.error("An error occurred while adding venue {}", venue.getVenueName(), e);
        }

    }
}