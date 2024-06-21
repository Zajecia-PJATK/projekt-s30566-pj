package pj.s30566.layout.loggedIn;

import pj.s30566.model.user.User;

public interface Navigation {
    void displayMenu(User user);
    int getUserChoice();
    void executeOption(int choice, User user);
}
