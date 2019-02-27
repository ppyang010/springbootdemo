package code.support;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author ccy
 * @description
 * @time 2019/2/27 上午10:59
 */
public class TiemGenUtil {

    public static String UTCTimeString(){
        String format = ZonedDateTime.now().plusHours(1).format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
        return format;
    }

    public static void main(String[] args) {
        System.out.println(UTCTimeString());
    }
}
