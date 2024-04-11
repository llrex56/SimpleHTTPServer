package springboot.demo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luozhenhong
 * @version 1.0
 * @date 2021/1/25 15:38
 */
public class Test2 {

    @Data
    static class A {
        private volatile boolean running = true;

        private List<Integer> list = new ArrayList<>();

        public synchronized void add(Integer item) {
            list.add(item);
            running = list.size() != 5;
        }

        public synchronized int size() {
            return list.size();
        }
    }

    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();



        A a = new A();

        new Thread(() -> {
            while (a.running) {};
            System.out.println("end.............");
        }, "t2").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                a.add(i+1);
//                System.out.println(i+1);
            }
        }, "t1").start();
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
