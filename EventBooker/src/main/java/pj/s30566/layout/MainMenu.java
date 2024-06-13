package pj.s30566.layout;


import java.util.Scanner;

public class MainMenu implements Navigation {
    private final Scanner scanner = new Scanner(System.in);
    public void displayMenu(){
        System.out.println("EventBooker - Niezapomniane wspomnienia");
        System.out.println("1. Zaloguj sie");
        System.out.println("2. Zarejestruj sie");
        System.out.println("3. Przegladaj nadchodzace wydarzenia");
        System.out.print("Wybierz opcje: ");
    }

    public int getUserChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
}
