package pj.s30566;
import pj.s30566.layout.GreetMenu;

public class Main {
    public static void main(String[] args) {
        int choice;
        do {
            GreetMenu greetMenu = new GreetMenu();
            greetMenu.displayMenu();
            choice = greetMenu.getUserChoice();
            greetMenu.executeOption(choice);
        } while (choice != 0);
    }
}