package com.example.service;

import com.example.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {
    int addStudent(Student student);
    List<Student> findStudents();
}
