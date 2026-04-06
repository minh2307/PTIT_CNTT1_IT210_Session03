package org.example.ss03.model.service;

import org.example.ss03.model.entity.Student;
import org.example.ss03.model.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Lấy toàn bộ danh sách (UC-01)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Tìm kiếm theo tên (UC-03)
    public List<Student> searchByName(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return getAllStudents();
        }

        return studentRepository.findAll().stream()
                .filter(s -> s.getFullName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Lọc theo khoa (UC-03)
    public List<Student> filterByFaculty(String faculty) {
        if (faculty == null || faculty.isEmpty()) {
            return getAllStudents();
        }

        return studentRepository.findAll().stream()
                .filter(s -> s.getFaculty().equalsIgnoreCase(faculty))
                .collect(Collectors.toList());
    }

    // Gộp search + filter + sort (UC-01 + UC-03)
    public List<Student> getStudents(String search, String faculty, String sortBy) {

        List<Student> students = studentRepository.findAll();

        // Search theo tên
        if (search != null && !search.isEmpty()) {
            students = students.stream()
                    .filter(s -> s.getFullName().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // Filter theo khoa
        if (faculty != null && !faculty.isEmpty()) {
            students = students.stream()
                    .filter(s -> s.getFaculty().equalsIgnoreCase(faculty))
                    .collect(Collectors.toList());
        }

        // Sort (phần này để người 4 làm, bạn để tạm hoặc bỏ cũng OK)
        if ("name".equalsIgnoreCase(sortBy)) {
            students = students.stream()
                    .sorted((a, b) -> a.getFullName().compareToIgnoreCase(b.getFullName()))
                    .collect(Collectors.toList());
        }

        if ("gpa".equalsIgnoreCase(sortBy)) {
            students = students.stream()
                    .sorted((a, b) -> Double.compare(b.getGpa(), a.getGpa()))
                    .collect(Collectors.toList());
        }

        return students;
    }

    // Lấy chi tiết sinh viên theo id
    public Student getStudentById(Long id) {
        if (id == null) {
            return null;
        }
        return studentRepository.findById(id);
    }
}