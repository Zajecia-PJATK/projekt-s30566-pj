package pj.s30566;
import pj.s30566.layout.MainMenu;
import pj.s30566.layout.Navigation;
import pj.s30566.loginUtils.Login;
import pj.s30566.registration.Register;

public class Main {
    public static void main(String[] args) {
        Navigation nav = new MainMenu();
        int choice;

        do {
            nav.displayMenu();
            choice = nav.getUserChoice();

            switch (choice){
                case 1:
                    Login login = new Login();
                    int validation = login.login();
                    if (validation == 0 || validation == -1){
                        System.out.println("Jest fajnie");
                    }
                    break;
                case 2:
                    Register register = new Register();
                    register.addUser();
                    break;
            }
        } while (choice !=0);
    }
}