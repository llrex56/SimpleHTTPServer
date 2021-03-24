package springboot.demo;

import lombok.*;
//import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author luozhenhong
 * @version 1.0
 * @date 2020/12/3 17:11
 */
public class BuilderTest {

    public static void main(String[] args) {
//        A a = A.builder().id("1").build();
//        B b = B.builder().id("2").name("zs").build();
//        System.out.println(a);
//        System.out.println(b);

        String s = get();
        System.out.println(s);

        List<String> strings = Arrays.asList("12,3,45".split(","));

        strings.forEach(System.out::println);
    }

    private static String get() {
        List<A> list = new ArrayList<>();
//        list.add(A.builder().build());

        return list.stream()
                .findFirst()
                .map(A::getId)
                .orElse(null);
    }
}

//@NoArgsConstructor
//@AllArgsConstructor
@Data
//@SuperBuilder
class A {
    private String id;
}

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//@NoArgsConstructor
//@AllArgsConstructor
//@SuperBuilder
class B extends A {
    private String name;
}
