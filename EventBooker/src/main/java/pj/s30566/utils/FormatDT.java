package pj.s30566.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatDT {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public String format(LocalDateTime time){
        return time.format(formatter);
    }
    public LocalDateTime getLocalDateTime(String time) {
        return LocalDateTime.parse(time, formatter);
    }
}
