package sjl;

import sjl.entity.HotelFacility;

/**
 * @author luozhenhong
 * @version 1.0
 * 2024/3/22 17:15
 */
public class HotelFacilityCache extends AbstractCache<HotelFacility> {

    private HotelFacilityCache() {
    }

    private static class LazyHolder {
        static final HotelFacilityCache INSTANCE = new HotelFacilityCache();
    }

    public static HotelFacilityCache getInstance() {
        return HotelFacilityCache.LazyHolder.INSTANCE;
    }

    @Override
    protected String getKey(HotelFacility item) {
        return item.getId();
    }

    @Override
    protected int getExcelSheetIndex() {
        return 0;
    }

    @Override
    protected String getFileRelativePath() {
        return "编码/设施早餐/设施早餐份（3.0）.xlsx";
    }

}

