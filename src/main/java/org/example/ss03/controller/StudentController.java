package org.example.ss03.controller;

import org.example.ss03.model.entity.Student;
import org.example.ss03.model.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String showStudents(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "faculty", required = false) String faculty,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            Model model) {

        List<Student> students = studentService.getStudents(search, faculty, sortBy);
        model.addAttribute("students", students);
        model.addAttribute("search", search);
        model.addAttribute("faculty", faculty);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("resultCount", students.size());

        return "students";
    }

    @GetMapping("/students/detail")
    public String showStudentDetail(@RequestParam(value = "id", required = false) Long id,
            Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "student-detail";
    }
}
