package org.example.ss03.controller;

import org.example.ss03.model.entity.Student;
import org.example.ss03.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Dashboard
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {

        List<Student> students = studentService.getAllStudents();

        // Tổng số sinh viên
        int totalStudents = students.size();

        // GPA trung bình
        double averageGpa = students.stream()
                .mapToDouble(Student::getGpa)
                .average()
                .orElse(0.0);

        // Thủ khoa
        Student topStudent = students.stream()
                .max((a, b) -> Double.compare(a.getGpa(), b.getGpa()))
                .orElse(null);

        // Tỷ lệ trạng thái
        Map<String, Long> statusCount = students.stream()
                .collect(Collectors.groupingBy(Student::getStatus, Collectors.counting()));

        Map<String, Double> statusPercentage = statusCount.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue() * 100.0 / totalStudents
                ));

        model.addAttribute("totalStudents", totalStudents);
        model.addAttribute("averageGpa", averageGpa);
        model.addAttribute("topStudent", topStudent);
        model.addAttribute("statusPercentage", statusPercentage);

        return "dashboard";
    }
}