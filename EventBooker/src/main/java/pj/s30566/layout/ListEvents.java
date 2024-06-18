package pj.s30566.layout;

import pj.s30566.model.Event;
import pj.s30566.utils.mysql.EventDriver;

import java.io.Console;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ListEvents {
    private final Scanner scanner = new Scanner(System.in);
    public void listEvents() {
        EventDriver eventDriver = new EventDriver();
        System.out.println("Nadchodzace wydarzenia: ");
        List<Event> eventList = eventDriver.getEvents();
        int elementCount = eventList.size();
        int index = 0;
        Console console = System.console();
        char input;
        while (true){
            System.out.println("Obiekt: " + (index + 1) + "/" + elementCount + " q - wyjdź, a - poprzedni element  , d - następny element");

            System.out.println("Nazwa: " + eventList.get(index).getEventName());
            System.out.println("Typ: " + eventList.get(index).getEventType().name());

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
