package lombok;

import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;

/**
 * @author luozhenhong
 * @version 1.0
 * 2022/11/1 15:31
 */
public class LombokTest {

    public static void main(String[] args) {
        System.out.println(Math.ceil(0.1));
        System.out.println(Math.ceil(1.1));
        System.out.println(Math.floor(51.1));
        System.out.println(BindActivityRequest.of(Arrays.asList("123", "456"), "", ""));
    }
}
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
class BindActivityRequest {
    @NonNull
    private List<String> couponCodes;
    @NonNull
    private String activityId;
    @NonNull
    private String activityName;

    private CouponActivityEnum activityType = CouponActivityEnum.ELIVE_ACT;
}

enum CouponActivityEnum {
    ELIVE_ACT
}