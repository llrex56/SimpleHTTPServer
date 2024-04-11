package sjl;

import sjl.entity.HotelBusinessCircle;

/**
 * @author luozhenhong
 * @version 1.0
 * 2024/3/22 17:15
 */
public class HotelBusinessCircleCache extends AbstractCache<HotelBusinessCircle> {

    private HotelBusinessCircleCache() {
    }

    private static class LazyHolder {
        static final HotelBusinessCircleCache INSTANCE = new HotelBusinessCircleCache();
    }

    public static HotelBusinessCircleCache getInstance() {
        return HotelBusinessCircleCache.LazyHolder.INSTANCE;
    }

    @Override
    protected String getKey(HotelBusinessCircle item) {
        return item.getBizzoneid();
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

