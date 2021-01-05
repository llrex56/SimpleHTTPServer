package springboot.demo;

import okhttp3.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springboot.demo.controller.TestLogbookController;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringbootApplicationTest {

    @Test
    public void testMulThreadSubmitOrder() throws InterruptedException {
        final int runCounter = 20;
        final CountDownLatch begin = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(runCounter);
        final ExecutorService exec = Executors.newFixedThreadPool(100);

        for (int i = 0; i < runCounter; i++) {
            final int No = i + 1;
            Runnable run = () -> {
                try {
                    begin.await();
                    // 提交订单
                    submitOrder(No);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    end.countDown();
                }
            };
            exec.submit(run);
        }
        System.out.println("Submit order begin ...");

        begin.countDown();
        end.await();

        System.out.println("Submit order end ...");

        exec.shutdown();
    }

    private void submitOrder(int No) throws IOException {
        String url = "http://localhost:8091/purchase/order/v3/submitOrder.do";
//        String url = "http://localhost:8091/purchase/order/pts/test";
        FormBody formBody = new FormBody.Builder()
                .add("totalAmount", "10.0")
                .add("ticketAmount", "0")
                .add("levelPriceId", "26075112424708586")
                .add("groupId", "1001676")
                .add("isActivity", "0")
                .add("groupNo", "1")
                .build();
        Headers headers = new Headers.Builder()
                .add("userId", "20200611112157000914")
                .add("token", "20200611112157000914:1639037096197:f8af5a1abb025ead3ced69ebf8196bbd")
                .add("sign", "e7af6c4869cd2988d1b20e8df63a953f")
                .add("deviceNo", "4FADFDFC-013E-4E53-B9C8-254CD70A2ADC")
                .add("deviceOSVer", "12.4.6")
                .add("appVersion", "5.2.0")
                .add("channel", "2")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .headers(headers)
                .build();

        Response response = new OkHttpClient().newCall(request).execute();

        String s = Objects.requireNonNull(response.body()).string();

        System.out.printf("No.%d %s\n", No, s);
    }

    @Test
    public void testMod() {
        System.out.println(50 % 10);
    }

    /**
     * {@link springboot.demo.controller.TestLogbookController#testJson(TestLogbookController.TestJsonParam)}
     */
    @Test
    public void test() {
        List mock = Mockito.mock(List.class);

        Mockito.when(mock.get(0)).thenReturn(1);

        System.out.println(mock.get(0));
    }


    /**
     * @see springboot.demo.controller.TestLogbookController#testGet(String)
     */
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
