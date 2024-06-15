package pj.s30566;
import pj.s30566.layout.GreetMenu;
import pj.s30566.layout.Navigation;
import pj.s30566.utils.login.Login;
import pj.s30566.utils.registration.Register;

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