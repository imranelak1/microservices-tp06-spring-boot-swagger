package com.example.studentmanagement.service;
import com.example.studentmanagement.entity.Student;import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import java.util.*;
/** Façade métier entre contrôleur et persistance. */
@Service @Transactional
public class StudentService {
    private final StudentRepository students;public StudentService(StudentRepository students){this.students=students;}
    public Student save(Student student){return students.save(student);}
    public boolean delete(int id){Student student=students.findById(id);if(student==null)return false;students.delete(student);return true;}
    @Transactional(readOnly=true) public List<Student> findAll(){return students.findAll();}
    @Transactional(readOnly=true) public long countStudents(){return students.count();}
    @Transactional(readOnly=true) public Collection<?> findNbrStudentByYear(){return students.findNbrStudentByYear();}
}
