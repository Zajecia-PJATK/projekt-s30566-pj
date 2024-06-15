package pj.s30566.layout;

import java.util.Scanner;

public class LoggedInMenu implements Navigation{
    private final Scanner scanner = new Scanner(System.in);
    public void displayMenu(){

    }

    public int getUserChoice(){
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public void executeOption(int choice){

    }
}
