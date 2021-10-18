package springboot.demo.tdd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import springboot.demo.domain.Student;
import springboot.demo.service.StudentService;

import java.util.List;

/**
 * @author luozhenhong
 * @version 1.0
 * @date 2021/7/14 16:48
 */
public class DBUnitTest extends AbstractDBUnitBase {


    @Autowired
    private StudentService studentService;


    @Test
    public void testListAll() {
        // 导入测试数据
        importTables("student-data.xml");
        List<Student> students = studentService.listAll();
        System.out.println(students);
    }
}
