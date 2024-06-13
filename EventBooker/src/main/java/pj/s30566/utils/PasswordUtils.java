package pj.s30566.utils;

import java.io.Console;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class PasswordUtils {
    public static String hashPassword(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPassword(){
        Console console = System.console();
        String password;
        Scanner scanner = new Scanner(System.in);
        if (console != null){
            char[] passwordChars = console.readPassword("Podaj haslo: ");
            password = String.valueOf(passwordChars);
            java.util.Arrays.fill(passwordChars, ' ');
        } else {
            System.out.println("!!! KONSOLA NIE JEST DOSTEPNA, TWOJE HASLO BEDZIE WIDOCZNE !!! ");
            System.out.println("Podaj haslo:");
            password = scanner.nextLine();
        }
        return password;
    }
}
