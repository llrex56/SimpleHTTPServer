package tdd.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import springboot.demo.dao.StudentDao;
import springboot.demo.domain.Student;

import java.util.List;

/**
 * @author luozhenhong
 * @version 1.0
 * 2023/4/19 9:41
 */
@SpringBootTest(classes = {DataSourceAutoConfiguration.class, MybatisAutoConfiguration.class, MybatisConfiguration.class})
public class StudentDaoTest {

    @Autowired
    private StudentDao studentDao;

    @Test
    public void should_list_all() {
        final List<Student> students = studentDao.listAll();
        System.out.println(students);
    }
}
@Configuration
@MapperScan("springboot.demo.dao")
class MybatisConfiguration {

}