package springboot.demo

import groovy.sql.Sql
import org.apache.tomcat.jdbc.pool.DataSource

class SpecUtils {

    private SpecUtils() {}

    static DataSource inMemoryDataSource() {
        return new DataSource().with { dataSource ->
            dataSource.driverClassName = 'org.h2.Driver'
            dataSource.url = 'jdbc:h2:mem:'
            dataSource.username = 'sa'
            dataSource.password = ''
            dataSource
        }
    }

    void createStudentTable(javax.sql.DataSource dataSource){
        assert dataSource
        new Sql(dataSource).execute("CREATE TABLE student(id INT PRIMARY KEY, firstname VARCHAR(255), lastname VARCHAR(255), age INT )")
    }

    void dropStudentTable(javax.sql.DataSource dataSource){
        assert dataSource
        new Sql(dataSource).execute("drop table student")
    }

}
