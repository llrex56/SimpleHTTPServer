package distance;

/**
 * Vincenty算法 计算两点之间的距离 (经纬度)
 *
 * @author luozhenhong
 * @version 1.0
 * 2023/11/23 12:02
 */
public class Vincenty {

    public static void main(String[] args) {
        double lat1 = 30.2853;
        double lon1 = 120.1535;
        double lat2 = 30.2853;
        double lon2 = 120.1535;
        System.out.println(distance(lat1, lon1, lat2, lon2));
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        if (lat1 == lat2 && lon1 == lon2) {
            return 0;
        }
        if (lat1 == lat2) {
            return Math.abs(lon1 - lon2);
        }
        if (lon1 == lon2) {
            return Math.abs(lat1 - lat2);
        }
        if (lat1 > lat2) {
            double temp = lat1;
            lat1 = lat2;
            lat2 = temp;
            temp = lon1;
            lon1 = lon2;
            lon2 = temp;
        }
        double dLat = (lat2 - lat1) * Math.PI / 180;
        double dLon = (lon2 - lon1) * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6371 * c;
    }
}
