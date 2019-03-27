package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 转换日期
 */
public class DateTo {
    /**
     * 将今天的日期转为“今天”而不是日期
     *
     * @param dateTime 字符串，需要更改的时间
     * @return 若为今天返回 今天 时：分 否则返回日期不返回时间
     */
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
