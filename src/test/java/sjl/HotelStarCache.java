package sjl;

import sjl.entity.HotelStar;

/**
 * @author luozhenhong
 * @version 1.0
 * 2024/3/22 17:15
 */
public class HotelStarCache extends AbstractCache<HotelStar> {

    private HotelStarCache() {
    }

    private static class LazyHolder {
        static final HotelStarCache INSTANCE = new HotelStarCache();
    }

    public static HotelStarCache getInstance() {
        return HotelStarCache.LazyHolder.INSTANCE;
    }

    @Override
    protected String getKey(HotelStar item) {
        return item.getCode();
    }

    @Override
    protected int getExcelSheetIndex() {
        return 0;
    }

    @Override
    protected String getFileRelativePath() {
        return "编码/星级品牌/酒店星级.xlsx";
    }

}

