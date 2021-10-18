package springboot.demo.controller;

import lombok.RequiredArgsConstructor;
import mo.aomi.common.kafka.KafkaSender;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author luozhenhong
 * @version 1.0
 * @date 2021/9/27 19:50
 */
@RestController
@RequestMapping("kafka")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaSender kafkaSender;

    ThreadPoolExecutor jdkExecutor = new ThreadPoolExecutor(
            10,
            20,
            3,
            TimeUnit.MINUTES,
            new SynchronousQueue<>(),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    @Resource(name = "taskExecutor")
    private Executor taskExecutor;

    {
        System.out.println("==== controller init");
    }

    @GetMapping("/send/{what}")
    public void send(@PathVariable("what") String what) {
        kafkaSender.send("test-topic", what);
    }

    @KafkaListener(topics = "test-topic", groupId = "test-group")
    public void listen(String what) {
        taskExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.printf("do biz... with params: [%s]\n", what);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 需处理应用异常关闭，线程任务未处理，而数据丢失问题
        // redis, 提交ack前记录日志，业务处理完更新日志状态。应用重启后先消费redis日志的数据
    }
}
