package springboot.demo.service

import org.junit.runner.RunWith
import org.spockframework.runtime.Sputnik
import spock.lang.Specification
import spock.lang.Unroll
import springboot.demo.dao.StudentDao
import springboot.demo.domain.Student
import springboot.demo.service.impl.StudentServiceImpl

@RunWith(Sputnik.class)
class DataDrivenSpec extends Specification {

  def studentDao = Mock(StudentDao)
  def studentService = new StudentServiceImpl(studentDao: studentDao);

  @Unroll
  def "student-list"() {
    given:
    def list = [new Student(id: 1, firstname: "Luo"), new Student(id: 2, firstname: "Zhang")]
    studentDao.findAll() >> list
    when:
    def result = studentService.listAll()
    then:
    result != null
    result.size() == 2
    result.forEach({e -> println e})
  }

  def "test1"() {
    given:
    studentDao.findAll() >> students
    when:
    def result = studentService.listAll()
    then:
    result != null
    result.size() == 2
    result.forEach({e -> println e})
    where:
    students        || r
    studentList()   || 2
  }

  def studentList() {
    return [
            new Student(id: 1, firstname: "Luo"),
            new Student(id: 2, firstname: "Zhang")
    ]
  }
}
