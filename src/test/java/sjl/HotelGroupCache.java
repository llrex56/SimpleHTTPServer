package sjl;

import sjl.entity.HotelGroup;

/**
 * @author luozhenhong
 * @version 1.0
 * 2024/3/22 17:15
 */
public class HotelGroupCache extends AbstractCache<HotelGroup> {

    private HotelGroupCache() {
    }

    private static class LazyHolder {
        static final HotelGroupCache INSTANCE = new HotelGroupCache();
    }

    public static HotelGroupCache getInstance() {
        return HotelGroupCache.LazyHolder.INSTANCE;
    }

    @Override
    protected String getKey(HotelGroup item) {
        return item.getHotelGroup();
    }

    @Override
    protected int getExcelSheetIndex() {
        return 0;
    }

    @Override
    protected String getFileRelativePath() {
        return "编码/星级品牌/酒店集团品牌1110.xlsx";
    }

}

