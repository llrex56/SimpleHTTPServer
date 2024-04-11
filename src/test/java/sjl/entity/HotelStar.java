package sjl.entity;

import lombok.Data;
import sjl.ExcelAlias;

/**
 * @author luozhenhong
 * @version 1.0
 * 2024/3/25 15:23
 */
@Data
public class HotelStar {

    @ExcelAlias("编码")
    private String code;

    @ExcelAlias("星级")
    private String star;

    @ExcelAlias("对应名称")
    private String name;
}
