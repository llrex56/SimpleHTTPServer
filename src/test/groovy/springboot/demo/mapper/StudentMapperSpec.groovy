package springboot.demo.mapper

import be.janbols.spock.extension.dbunit.DbUnit
import groovy.sql.Sql
import org.apache.tomcat.jdbc.pool.DataSource
import spock.lang.Shared
import spock.lang.Specification
import springboot.demo.SpecUtils

class StudentMapperSpec extends Specification {

    @Shared
    DataSource dataSource

    def setupSpec() {
        dataSource = SpecUtils.inMemoryDataSource()
        dataSource?.with {it ->
            new Sql(it).execute("CREATE TABLE student(id INT PRIMARY KEY, firstname VARCHAR(255), lastname VARCHAR(255), age INT )")
        }
    }

    def cleanupSpec() {
        dataSource?.with {it ->
            new Sql(it).execute("drop table student")
        }
    }

    @DbUnit(
            content = {
                student(id: 1, firstname: "Luo", lastname: "ZhenHong", age: 3)
                student(id: 2, firstname: "Zhang", lastname: "San", age: 4)
            }
    )
    def "student list"() {
        when:
        def result = new Sql(dataSource).rows("select * from student")
        then:
        result != null
        result.size() == 2
        result.forEach({e -> println e})
    }
}
