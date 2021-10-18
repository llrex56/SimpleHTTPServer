package springboot.demo.tdd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.StringUtils;
import springboot.demo.BaseTest;

/**
 * 123
 * @author luozhenhong
 * @version 1.0
 * @date 2021/3/24 14:16
 */
public class TddTest01 extends BaseTest {

    @Test
    public void should_give_negative_result_for_empty_string() {
        String password = "";

        boolean result = new Password(password).isPasswordStrong();

        Assert.assertFalse(result);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Password {

        private String password;

        /**
         * is password strong
         *
         * @return password is strong or weak
         */
        public boolean isPasswordStrong() {
            if (StringUtils.isEmpty(password)) {
                return false;
            }
            return true;
        }
    }
}
