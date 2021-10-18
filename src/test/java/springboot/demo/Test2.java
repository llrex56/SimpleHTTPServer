package springboot.demo;

import java.util.Random;

/**
 * @author luozhenhong
 * @version 1.0
 * @date 2021/1/25 15:38
 */
public class Test2 {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(getRandomTimeout(60 * 36, 0, 60));
        }
//        test();
    }

    /**
     * 从x到y的随机数
     */
    public static long getRandomTimeout(long seconds, int x, int y) {
        if (seconds == -1) {
            return -1;
        }
        return seconds + Math.round(Math.random() * (y - x)) + x;
    }

    public static void test() {
        int flag = 37*60;
        int time = flag;
        int tmp = 0;

        for (int i = 0; i < 100; i++) {
            time = time + flag;
            tmp = time % 360;

            if (tmp < 1) {
                System.out.println(time);
            }
        }
    }
}
