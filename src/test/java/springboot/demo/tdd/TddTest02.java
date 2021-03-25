package springboot.demo.tdd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

/**
 * FizzBuzz
 * @author luozhenhong
 * @version 1.0
 * @date 2021/3/24 14:34
 */
public class TddTest02 {

    /*
     * Product backlog
     * 1. 从 1 至 100 依次报数，如第 1 位报 “1”，第 2 位报 “2”
     * 2. 如果碰到被 3 整除的数则报 “Fizz”
     * 3. 如果碰到被 5 整除的数则报 “Buzz”
     * 4. 如果同时被 3 和 5 整除则报 “FizzBuzz”
     */

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = { -1, 0, 101 })
    void should_throw_illegal_argument_exception_when_input_invalid_number(Integer input) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FizzBuzz(input).toString());
    }

    @ParameterizedTest(name = "should return {1} when input {0}")
    @CsvSource({
            "1, 1",
            "2, 2",
            "3, Fizz",
            "5, Buzz",
            "15, FizzBuzz",
            "20, Buzz",
            "100, Buzz"
    })
    void should_return_expected_when_input_number(int input, String expected) {
        String result = new FizzBuzz(input).toString();
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(expected);
    }
}

interface Executable {

    String exec(int number);
}

class DivRule implements Executable {
    private int radix;
    private final String txt;

    public DivRule(int radix, String txt) {
        this.radix = radix;
        this.txt = txt;
    }

    public static Executable create(int radix, String txt) {
        return new DivRule(radix, txt);
    }

    @Override
    public String exec(int number) {
        if (number % this.radix == 0) {
            return this.txt;
        }
        return "";
    }
}

class Rules {

    public static List<Executable> all() {
        return Arrays.asList(
                DivRule.create(3, "Fizz"),
                DivRule.create(5, "Buzz")
        );
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class FizzBuzz {

    private Integer number;

    public String toString() {
        org.springframework.util.Assert.notNull(number, "Number can not be null!");
        org.springframework.util.Assert.isTrue(number >= 1 && number <= 100, "Number must between 1 to 100!");

        return Rules.all()
                .stream()
                .map(e -> e.exec(this.number))
                .filter(e -> !e.isEmpty())
                .reduce(String::concat)
                .orElseGet(() -> number.toString());
    }
}