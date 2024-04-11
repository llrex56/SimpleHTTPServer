package distance;

/**
 * Haversine Distance 计算两点之间距离
 *
 * @author luozhenhong
 * @version 1.0
 * 2023/11/23 11:57
 */
public class Haversine {

    public static void main(String[] args) {
        double lat1 = 39.90403;
        double lng1 = 116.407528;
        double lat2 = 39.90403;
        double lng2 = 116.407528;
        System.out.println(getDistance(lat1, lng1, lat2, lng2));
    }

    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        if (lat1 == lat2 && lng1 == lng2) {
            return 0;
        }
        if (lat1 == lat2) {
            return Math.abs(lng1 - lng2);
        }
        if (lng1 == lng2) {
            return Math.abs(lat1 - lat2);
        }
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return 6378137 * c;
    }
}
