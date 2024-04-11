package sjl;

import sjl.entity.HotelArea;

/**
 * @author luozhenhong
 * @version 1.0
 * 2024/3/22 17:15
 */
public class HotelAreaCache extends AbstractCache<HotelArea> {

    private HotelAreaCache() {
    }

    private static class LazyHolder {
        static final HotelAreaCache INSTANCE = new HotelAreaCache();
    }

    public static HotelAreaCache getInstance() {
        return HotelAreaCache.LazyHolder.INSTANCE;
    }

    @Override
    protected String getKey(HotelArea item) {
        return item.getZoneid();
    }

    @Override
    protected int getExcelSheetIndex() {
        return 1;
    }

    @Override
    protected String getFileRelativePath() {
        return "编码/国家省城市商圈/深捷旅国家省份城市行政区.xlsx";
    }

}