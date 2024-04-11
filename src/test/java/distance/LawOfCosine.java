package distance;

/**
 * LawOfCosine 计算两个地理坐标点之间的距离
 *
 * @author luozhenhong
 * @version 1.0
 * 2023/11/23 12:04
 */
public class LawOfCosine {

    public static void main(String[] args) {
        double lat1 = 39.90403;
        double lng1 = 116.407528;
        double lat2 = 39.90403;
        double lng2 = 116.407528;
        double distance = getDistance(lat1, lng1, lat2, lng2);
        System.out.println(distance);
    }

    /**
     * 计算两个地理坐标点之间的距离
     *
     * @param lat1 第一个点的纬度
     * @param lng1 第一个点的经度
     * @param lat2 第二个点的纬度
     * @param lng2 第二个点的经度
     * @return 两个点之间的距离，单位为千米
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        if (lat1 == lat2 && lng1 == lng2) {
            return 0;
        } else {
            double radLat1 = Math.toRadians(lat1);
            double radLat2 = Math.toRadians(lat2);
            double a = radLat1 - radLat2;
            double b = Math.toRadians(lng1) - Math.toRadians(lng2);
            double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
            s = s * 6378137.0;
            s = Math.round(s * 10000000) / 10000000.0;
            return s;
        }
    }
}
