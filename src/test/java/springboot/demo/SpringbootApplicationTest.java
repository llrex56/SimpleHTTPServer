package springboot.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.function.BiFunction;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringbootApplicationTest {

    @Test
    public void test() {
        List mock = Mockito.mock(List.class);

        Mockito.when(mock.get(0)).thenReturn(1);

        System.out.println(mock.get(0));
    }


    @Test
    public void testFunction() {
        BiFunction<Integer, Integer, Integer> add = SpringbootApplicationTest::add;
        BiFunction<Integer, Integer, Integer> multi = SpringbootApplicationTest::multi;

        Integer apply = add.apply(1, 2);

        Integer apply1 = multi.apply(1, 2);

        System.out.println(apply);
        System.out.println(apply1);

    }

    private static Integer add(Integer x, Integer y) {
        return x + y;
    }

    private static Integer multi(Integer x, Integer y) {
        return x * y;
    }
}
