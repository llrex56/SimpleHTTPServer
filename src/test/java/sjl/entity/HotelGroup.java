package sjl.entity;

import lombok.Data;
import sjl.ExcelAlias;

/**
 * @author luozhenhong
 * @version 1.0
 * 2024/3/25 15:18
 */
@Data
public class HotelGroup {

    private String hotelGroup;

    private String typecode;

    private String typename;

    @ExcelAlias("简称")
    private String groupShortName;

    @ExcelAlias("名称")
    private String groupName;
}
