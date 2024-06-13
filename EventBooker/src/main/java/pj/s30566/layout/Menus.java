package pj.s30566.layout;


import pj.s30566.registration.Register;

import java.util.Scanner;

public class Menus {
    public static void mainMenu(){
        System.out.println("EventBooker - Niezapomniane wspomnienia");
        System.out.println("Zaloguj sie (1), Zarejetruj sie (2)");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option){
            case 2:
                Register register = new Register();
                register.addUser();
                break;
            case 1:
                System.out.println("Opcja jeszcze nie dostepna.");
                break;
        }
        System.out.println("KONCZE ELO");

    }
}
