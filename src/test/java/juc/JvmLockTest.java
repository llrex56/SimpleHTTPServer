package juc;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luozhenhong
 * @version 1.0
 * 2022/7/4 9:57
 */
public class JvmLockTest {

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.tryLock(3, TimeUnit.SECONDS);
        reentrantLock.lockInterruptibly();


        AtomicInteger atomicInteger = new AtomicInteger(1);
        atomicInteger.incrementAndGet();


        RedissonClient redissonClient = redissonClient();
        RLock redissonLock = redissonClient.getLock("");
        redissonLock.tryLock(3, TimeUnit.SECONDS);
        redissonLock.lock();


        new HashMap<>();

        new ConcurrentHashMap<>();
    }

    public static RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://".concat("").concat(":").concat(""))
                .setPassword("");
        return Redisson.create(config);
    }
}
