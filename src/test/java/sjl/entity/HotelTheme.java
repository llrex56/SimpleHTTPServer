package sjl.entity;

import lombok.Data;
import sjl.ExcelAlias;

/**
 * @author luozhenhong
 * @version 1.0
 * 2024/3/22 18:45
 */
@Data
public class HotelTheme {

    @ExcelAlias("ID")
    private String id;

    private String code;

    @ExcelAlias("酒店主题")
    private String theme;
}
