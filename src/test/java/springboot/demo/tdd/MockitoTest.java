package springboot.demo.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import springboot.demo.dao.StudentDao;
import springboot.demo.domain.Student;
import springboot.demo.domain.User;
import springboot.demo.service.StudentService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luozhenhong
 * @version 1.0
 * @date 2021/3/25 15:00
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentDao studentDao;

    @Test
    void testListAll() {

        Mockito.when(studentDao.listAll())
                .thenReturn(
                        new ArrayList<Student>(){{
                            add(new Student(1, "f-1", "l-1", 11));
                            add(new Student(2, "f-2", "l-2", 12));
                            add(new Student(3, "f-3", "l-3", 13));
                        }}
                );

        List<Student> students = studentService.listAll();

        students.forEach(System.out::println);

        Assertions.assertEquals(0, students.size());

        Mockito.verify(studentDao, Mockito.times(1)).listAll();
    }

    @Test
    void testStub() {
        List<User> list = Mockito.mock(List.class);

        Mockito.when(list.get(0))
                .thenReturn(
                        User.builder()
                                .id("1")
                                .name("zs")
                                .age(3)
                                .build()
                );
        Mockito.when(list.get(1)).thenThrow(IndexOutOfBoundsException.class);

        Assertions.assertEquals("zs", list.get(0).getName());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));

        Assertions.assertNull(list.get(3));
    }
}
