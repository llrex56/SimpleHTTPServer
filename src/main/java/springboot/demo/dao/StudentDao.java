package springboot.demo.dao;

import springboot.demo.domain.Student;

import java.util.List;

public interface StudentDao {

    List<Student> listAll();
}
