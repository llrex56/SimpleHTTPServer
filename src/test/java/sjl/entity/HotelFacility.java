package sjl.entity;

import lombok.Data;
import sjl.ExcelAlias;

/**
 * @author luozhenhong
 * @version 1.0
 * 2024/3/25 18:52
 */
@Data
public class HotelFacility {

    @ExcelAlias("酒店设施名称")
    private String name;

    @ExcelAlias("酒店设施编号")
    private String id;
}
