package sjl;

/**
 * @author luozhenhong
 * @version 1.0
 * 2024/3/22 17:15
 */
public class HotelRoomFacilityCache extends AbstractCache<HotelRoomFacility> {

    private HotelRoomFacilityCache() {
    }

    private static class LazyHolder {
        static final HotelRoomFacilityCache INSTANCE = new HotelRoomFacilityCache();
    }

    public static HotelRoomFacilityCache getInstance() {
        return HotelRoomFacilityCache.LazyHolder.INSTANCE;
    }

    @Override
    protected String getKey(HotelRoomFacility item) {
        return item.getId();
    }

    @Override
    protected int getExcelSheetIndex() {
        return 2;
    }

    @Override
    protected String getFileRelativePath() {
        return "编码/设施早餐/设施早餐份（3.0）.xlsx";
    }

}

