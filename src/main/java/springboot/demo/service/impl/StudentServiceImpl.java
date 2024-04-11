package springboot.demo.service.impl;

import org.springframework.stereotype.Service;
import springboot.demo.dao.StudentDao;
import springboot.demo.domain.Student;
import springboot.demo.service.StudentService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;

    public List<Student> listAll() {
        return studentDao.listAll();
    }
}
