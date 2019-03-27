package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTo {
    public static String dateTotoday(String dateTime) {
        SimpleDateFormat dateCurrent = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        if (dateTime.substring(0, 10).equals(dateCurrent.format(date))) {
            StringBuilder today = new StringBuilder("今天");
            today.append(dateTime.substring(dateTime.indexOf(" ")));

            return today.toString();
        } else {
            return dateTime.substring(0, 10);
        }
    }
}
