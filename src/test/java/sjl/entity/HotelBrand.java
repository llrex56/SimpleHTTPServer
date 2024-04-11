package sjl.entity;

import lombok.Data;
import sjl.ExcelAlias;

/**
 * @author luozhenhong
 * @version 1.0
 * 2024/3/25 14:05
 */
@Data
public class HotelBrand {

    private String hotelBrand;

    private String modulecode;

    private String modulename;

    private String typecode;

    private String typename;

    @ExcelAlias("简称")
    private String brandShortName;

    @ExcelAlias("名称")
    private String brandName;
}
