package com.example.service.impl;

import com.example.dao.StudentDao;
import com.example.entity.Student;
import com.example.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentDao studentDao;
    @Override
    public int addStudent(Student student) {return studentDao.insertStudent(student);}
    @Override
    public List<Student> findStudents() {
        return studentDao.selectStudents();
    }
}
