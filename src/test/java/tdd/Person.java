package tdd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author luozhenhong
 * @version 1.0
 * 2023/4/24 15:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor(staticName = "of")
public class Person extends BaseObj implements Serializable {
    private String id;
    private String name;
}
