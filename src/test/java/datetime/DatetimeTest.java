package datetime;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author luozhenhong
 * @version 1.0
 * 2024/3/16 15:22
 */
public class DatetimeTest {

    public static void main(String[] args) {
        // 获取当前UTC时间戳（毫秒）
        //long utcTimestamp = Instant.now().toEpochMilli();
        final ZoneId utcZoneId = ZoneId.of("UTC");
        final ZonedDateTime utcZonedDateTime = ZonedDateTime.now(utcZoneId);
        final long utcTimestamp = utcZonedDateTime.toInstant().toEpochMilli();
        System.out.println("UTC Timestamp: " + utcTimestamp);

        // 获取当前东八区（CST）时间戳（毫秒）
        ZoneId cstZoneId = ZoneId.of("Asia/Shanghai"); // 东八区时区ID
        ZonedDateTime cstZonedDateTime = ZonedDateTime.now(cstZoneId);
        long cstTimestamp = cstZonedDateTime.toInstant().toEpochMilli();
        System.out.println("CST (East 8) Timestamp: " + cstTimestamp);

        // 获取当前东九区时间戳（毫秒）
        ZoneId estZoneId = ZoneId.of("Asia/Tokyo"); // 东九区时区ID，以东京为例
        ZonedDateTime estZonedDateTime = ZonedDateTime.now(estZoneId);
        long estTimestamp = estZonedDateTime.toInstant().toEpochMilli();
        System.out.println("EST (East 9) Timestamp: " + estTimestamp);

        // 打印当前UTC、东八区和东九区时间
        Instant utcInstant = Instant.now();
        ZonedDateTime cstZdt = utcInstant.atZone(cstZoneId);
        ZonedDateTime estZdt = utcInstant.atZone(estZoneId);
        System.out.println("UTC Time: " + utcInstant);
        System.out.println("CST (East 8) Time: " + cstZdt);
        System.out.println("EST (East 9) Time: " + estZdt);
    }
}
