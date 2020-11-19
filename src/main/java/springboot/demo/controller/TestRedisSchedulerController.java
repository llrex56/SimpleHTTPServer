package springboot.demo.controller;

import com.github.davidmarquis.redisscheduler.RedisTaskScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * @author luozhenhong
 * @version 1.0
 * @date 2020/10/12 9:40
 */
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TestRedisSchedulerController {

    //private final RedisTaskScheduler redisTaskScheduler;

    @GetMapping("/test/{id}")
    @ResponseBody
    public Object test(@PathVariable("id") String taskId) {
        Instant triggerTime = Instant.now().plusSeconds(TimeUnit.SECONDS.toSeconds(500));
        //redisTaskScheduler.scheduleAt(taskId, triggerTime);

        return triggerTime;
    }
}
