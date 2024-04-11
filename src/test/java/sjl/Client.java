package sjl;

/**
 * @author luozhenhong
 * @version 1.0
 * 2024/3/25 14:41
 */
public class Client {

    public static void main(String[] args) {

        System.out.println(HotelThemeCache.getInstance().get("vacation"));
        System.out.println(HotelThemeCache.getInstance().get("vacation"));

        System.out.println(HotelBrandCache.getInstance().get("2040"));
        System.out.println(HotelBrandCache.getInstance().get("2041"));

        System.out.println(HotelGroupCache.getInstance().get("61"));
        System.out.println(HotelGroupCache.getInstance().get("62"));

        System.out.println(HotelStarCache.getInstance().get("20"));
        System.out.println(HotelStarCache.getInstance().get("25"));
        System.out.println(HotelStarCache.getInstance().get("30"));
        System.out.println(HotelStarCache.getInstance().get("40"));

        System.out.println(HotelAreaCache.getInstance().get("30000186"));
        System.out.println(HotelAreaCache.getInstance().get("30000659"));

        System.out.println(HotelBusinessCircleCache.getInstance().get("30001936"));
        System.out.println(HotelBusinessCircleCache.getInstance().get("30001937"));
        System.out.println(HotelBusinessCircleCache.getInstance().get("30001938"));
        System.out.println(HotelBusinessCircleCache.getInstance().get("30001939"));

        // 酒店设施
        System.out.println(HotelFacilityCache.getInstance().get("33"));
        System.out.println(HotelFacilityCache.getInstance().get("34"));
        System.out.println(HotelFacilityCache.getInstance().get("40"));
        System.out.println(HotelFacilityCache.getInstance().get("42"));

        // 房型设施
        System.out.println(HotelRoomFacilityCache.getInstance().get("11"));
        System.out.println(HotelRoomFacilityCache.getInstance().get("12"));
        System.out.println(HotelRoomFacilityCache.getInstance().get("39"));

    }
}
