package springboot.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
}
