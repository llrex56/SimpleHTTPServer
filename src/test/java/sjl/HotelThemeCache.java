package sjl;

import sjl.entity.HotelTheme;

/**
 * @author luozhenhong
 * @version 1.0
 * 2024/3/22 17:15
 */
public class HotelThemeCache extends AbstractCache<HotelTheme> {

    private HotelThemeCache() {
    }

    private static class LazyHolder {
        static final HotelThemeCache INSTANCE = new HotelThemeCache();
    }

    public static HotelThemeCache getInstance() {
        return HotelThemeCache.LazyHolder.INSTANCE;
    }

    @Override
    protected String getKey(HotelTheme item) {
        return item.getCode();
    }

    @Override
    protected int getExcelSheetIndex() {
        return 0;
    }

    @Override
    protected String getFileRelativePath() {
        return "编码/星级品牌/酒店主题4.13.xlsx";
    }

}

