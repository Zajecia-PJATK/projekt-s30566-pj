package pj.s30566.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pj.s30566.utils.mysql.LocationDriver;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class SystemIdentifier {
    private static final Logger logger = LoggerFactory.getLogger(LocationDriver.class);
    public static String getMacAddress(){
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localhost);
            if (networkInterface == null) {
                Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
                while (interfaces.hasMoreElements()) {
                    networkInterface = interfaces.nextElement();
                    if (networkInterface != null && networkInterface.getHardwareAddress() != null) {
                        break;
                    }
                }
            }
            byte[] mac = networkInterface.getHardwareAddress();
            if (mac == null){
                return null;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i< mac.length; i++){
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            return sb.toString();
        } catch (UnknownHostException | SocketException e){
            logger.error("Problem z pobraniem adresu MAC", e);

        }
        return null;
    }

    public static String getUsername(){
        return System.getProperty("user.name");
    }
}
