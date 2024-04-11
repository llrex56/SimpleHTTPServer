package sjl;

import lombok.Data;

/**
 * @author luozhenhong
 * @version 1.0
 * 2024/3/25 18:52
 */
@Data
public class HotelRoomFacility {

    @ExcelAlias("设施ID")
    private String id;

    @ExcelAlias("房型设施")
    private String name;
}
