package jl;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author luozhenhong
 * @version 1.0
 * 2023/8/2 17:22
 */
public class VoidTest {

    @Test
    public void voidTest() {

        final Void result = fn(1, "2");

        System.out.println(result);
    }

    private Void fn(Object... arrays) {
        Arrays.stream(arrays).forEach(System.out::println);
        return null;
    }
}
