package pj.s30566.utils.event;

import pj.s30566.model.Event;
import pj.s30566.utils.output.Wipe;

import java.util.Scanner;

public class EventManager {
    Scanner scanner = new Scanner(System.in);

    public void createNewEvent(Event event){
        Wipe.wipe();
        System.out.println("=== Nowe Wydarzenie ===");
        System.out.println();
        System.out.println();
    }

}
