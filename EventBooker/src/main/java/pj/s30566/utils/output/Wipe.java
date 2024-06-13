package pj.s30566.utils.output;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pj.s30566.utils.mysql.UserDriver;

public class Wipe {
    private static final Logger logger = LoggerFactory.getLogger(UserDriver.class);
    public static void wipe(){
        try {
            if (System.getProperty("os.name").startsWith("Windows")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e){
            logger.error("An unexpected error while wiping console", e);
        }
    }
}
