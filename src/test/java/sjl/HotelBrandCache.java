package sjl;

import sjl.entity.HotelBrand;

/**
 * @author luozhenhong
 * @version 1.0
 * 2024/3/22 17:15
 */
public class HotelBrandCache extends AbstractCache<HotelBrand> {

    private HotelBrandCache() {
    }

    private static class LazyHolder {
        static final HotelBrandCache INSTANCE = new HotelBrandCache();
    }

    public static HotelBrandCache getInstance() {
        return HotelBrandCache.LazyHolder.INSTANCE;
    }

    @Override
    protected String getKey(HotelBrand item) {
        return item.getHotelBrand();
    }

    @Override
    protected int getExcelSheetIndex() {
        return 1;
    }

    @Override
    protected String getFileRelativePath() {
        return "编码/星级品牌/酒店集团品牌1110.xlsx";
    }

}

