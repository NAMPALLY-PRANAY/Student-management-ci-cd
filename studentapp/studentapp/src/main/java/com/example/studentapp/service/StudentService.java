package com.example.studentapp.service;

import com.example.studentapp.model.Student;
import com.example.studentapp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    
    public void addStudent(Student student) {
        repo.save(student);
    }

    
    public Student getStudentById(int id) {
        return repo.findById(id).orElse(null);
    }

    
    public void updateStudent(Student student) {
        repo.save(student);
    }

    
    public void deleteStudent(int id) {
        repo.deleteById(id);
    }

    
    public void deleteAllStudents() {
        repo.deleteAll();
    }

    
    
    public void updateAllStudents(Student updateData) {
        List<Student> students = repo.findAll();
        for (Student s : students) {
            if (updateData.getName() != null) s.setName(updateData.getName());
            if (updateData.getEmail() != null) s.setEmail(updateData.getEmail());
            if (updateData.getDepartment() != null) s.setDepartment(updateData.getDepartment());
            repo.save(s);
        }
    }

    public List<Student> addStudents(List<Student> students) {
        return repo.saveAll(students);
    }
}
