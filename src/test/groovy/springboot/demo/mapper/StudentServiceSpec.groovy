package springboot.demo.mapper

import be.janbols.spock.extension.dbunit.DbUnit
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import springboot.demo.service.StudentService

import javax.sql.DataSource

@SpringBootTest
class StudentServiceSpec extends Specification {

    @Autowired
    DataSource dataSource

    @Autowired
    StudentService studentService

    @DbUnit(
            content = {
                student(id: 1, firstname: "Luo", lastname: "ZhenHong", age: 3)
                student(id: 2, firstname: "Zhang", lastname: "San", age: 4)
            }
    )
    def "student list"() {
        given:
        when:
        def result = studentService.listAll()
        then:
        result != null
        result.size() == 2
        result.forEach({e -> println e})
    }
}
