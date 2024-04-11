package tdd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author luozhenhong
 * @version 1.0
 * 2023/4/19 11:53
 */
public class MethodSourceTest {

    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String str, int num, List<Person> list) {
        assertThat(str).hasSize(5);
        assertThat(num).isGreaterThanOrEqualTo(1).isLessThanOrEqualTo(2);
        assertThat(list).hasSize(2);
    }

    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                Arguments.arguments("apple", 1, Arrays.asList(Person.of("1", "zs"), Person.of("2", "ls"))),
                Arguments.arguments("lemon", 2, Arrays.asList(Person.of("3", "ww"), Person.of("4", "zl")))
        );
    }
}

@Data
class BaseObj implements Serializable {
    private String no;
}
