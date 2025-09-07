package com.example.studentapp.controller;

import com.example.studentapp.model.Student;
import com.example.studentapp.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    private final StudentService studentService;
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return student;
    }

    // Add: POST multiple students
    @PostMapping("/batch")
    public List<Student> addStudents(@RequestBody List<Student> students) {
        return studentService.addStudents(students);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student student) {
        student.setId(id);
        studentService.updateStudent(student);
        return student;
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return "Deleted student with id " + id;
    }

    // ✅ Delete all students
    @DeleteMapping
    public String deleteAllStudents() {
        studentService.deleteAllStudents();
        return "Deleted all students";
    }

    // ✅ Update all students (example: update department for all)
    @PutMapping
    public List<Student> updateAllStudents(@RequestBody Student updateData) {
        studentService.updateAllStudents(updateData);
        return studentService.getAllStudents();
    }
}
