package springboot.demo;

import com.github.davidmarquis.redisscheduler.RedisDriver;
import com.github.davidmarquis.redisscheduler.RedisTaskScheduler;
import com.github.davidmarquis.redisscheduler.drivers.spring.RedisTemplateDriver;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;

@SpringBootApplication              // Spring Boot 应用的标识
//@EnableCaching                      // 开启缓存
@EnableTransactionManagement        // 开启事务管理
@MapperScan("springboot.demo.dao")  // mapper 接口类扫描包配置
public class SpringbootApplication {

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(SpringbootApplication.class, args);
    }

//    @Bean
    public RedisTaskScheduler redisTaskScheduler(StringRedisTemplate redisTemplate) {
        RedisDriver driver = new RedisTemplateDriver(redisTemplate);
        RedisTaskScheduler result = new RedisTaskScheduler(driver, taskId -> {
            System.err.printf("task running... with param: [%s], time: [%s] \n", taskId, new Date().toInstant());
        });
        // redis-key: redis-scheduler.rex
        result.setSchedulerName("rex");
        result.setPollingDelayMillis(5000);
        result.setMaxRetriesOnConnectionFailure(5);
        return result;
    }
}
