package pj.s30566.layout;

import pj.s30566.model.Location;
import pj.s30566.model.user.User;
import pj.s30566.utils.mysql.LocationDriver;

import java.io.Console;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ListLoactions {

    private final Scanner scanner = new Scanner(System.in);
    public Location listLocations(User user) {
        LocationDriver locationDriver = new LocationDriver();
        System.out.println("Twoje lokacje: ");
        List<Location> locations = locationDriver.getLocationsByUser(user.getID());
        int elementCount = locations.size();
        int index = 0;
        Console console = System.console();
        char input;
        while (true){
            System.out.println("Obiekt: " + (index + 1) + "/" + elementCount + " q - wyjdź, a - poprzedni element  , d - następny element");
            System.out.println();
            System.out.println("Nazwa: " + locations.get(index).getLocationName());
            System.out.println("Adres: " + locations.get(index).getAddress());
            System.out.println(locations.get(index).getPostalCode() + "\t" + locations.get(index).getCity() + "\t");
            System.out.println(locations.get(index).getCountry().name());
            System.out.println("ID lokacji: " + locations.get(index).getLocationID());
            System.out.println();
            System.out.println("Wpisz 'e' aby wybrac te lokacje");

            if (console != null){
                try {
                    input = (char) console.reader().read();
                } catch (IOException e){
                    System.out.println("Problem z konsolą.");
                    input = scanner.next().charAt(0);
                }
                if (input == 'a'){
                    index = (index - 1 + locations.size()) % locations.size();
                } else if (input == 'd'){
                    index = (index + 1) % locations.size();
                } else if (input == 'e'){
                    return locations.get(index);
                } else if (input == 'q') {
                    break;
                } else {
                    System.out.println("Zły przycisk!");
                }
            } else {
                input = scanner.next().charAt(0);
                if (input == 'a'){
                    index = (index - 1 + locations.size()) % locations.size();
                } else if (input == 'd'){
                    index = (index + 1) % locations.size();
                } else if (input == 'e'){
                    return locations.get(index);
                } else if (input == 'q') {
                    break;
                } else {
                    System.out.println("Zły przycisk!");
                }
            }
        }
        return null;
    }

    public Location.Venue selectVenue(Location location){
        LocationDriver locationDriver = new LocationDriver();
        System.out.println("Sale w " + location.getLocationName());
        List<Location.Venue> venues = locationDriver.getVenuesByLocationID(location.getLocationID());
        int elementCount = venues.size();
        int index = 0;
        Console console = System.console();
        char input;
        while (true){
            System.out.println("Obiekt: " + (index + 1) + "/" + elementCount + " q - wyjdź, a - poprzedni element  , d - następny element");
            System.out.println();
            System.out.println("ID: " + venues.get(index).getVenueId());
            System.out.println("Nazwa: " + venues.get(index).getVenueName());
            System.out.println("Pojemnosc: : " + venues.get(index).getCapacity());
            System.out.println();
            System.out.println("Wpisz 'e' aby wybrac te lokacje");

            if (console != null){
                try {
                    input = (char) console.reader().read();
                } catch (IOException e){
                    System.out.println("Problem z konsolą.");
                    input = scanner.next().charAt(0);
                }
                if (input == 'a'){
                    index = (index - 1 + venues.size()) % venues.size();
                } else if (input == 'd'){
                    index = (index + 1) % venues.size();
                } else if (input == 'e'){
                    return venues.get(index);
                } else if (input == 'q') {
                    break;
                } else {
                    System.out.println("Zły przycisk!");
                }
            } else {
                input = scanner.next().charAt(0);
                if (input == 'a'){
                    index = (index - 1 + venues.size()) % venues.size();
                } else if (input == 'd'){
                    index = (index + 1) % venues.size();
                } else if (input == 'e'){
                    return venues.get(index);
                } else if (input == 'q') {
                    break;
                } else {
                    System.out.println("Zły przycisk!");
                }
            }
        }

        return null;
    }


}
