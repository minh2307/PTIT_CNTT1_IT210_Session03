package org.example.ss03.service;

import org.example.ss03.model.entity.Student;
import org.example.ss03.model.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Lấy toàn bộ danh sách sinh viên
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Tìm kiếm theo tên
    public List<Student> searchByName(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllStudents();
        }

        String lowerKeyword = keyword.trim().toLowerCase();

        return studentRepository.findAll().stream()
                .filter(student -> student.getFullName() != null
                        && student.getFullName().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }

    // Lọc theo khoa
    public List<Student> filterByFaculty(String faculty) {
        if (faculty == null || faculty.trim().isEmpty()) {
            return getAllStudents();
        }

        String normalizedFaculty = faculty.trim().toLowerCase();

        return studentRepository.findAll().stream()
                .filter(student -> {
                    if (student.getFaculty() == null) {
                        return false;
                    }

                    String studentFaculty = student.getFaculty().trim().toLowerCase();

                    return studentFaculty.equals(normalizedFaculty)
                            || ("cntt".equals(normalizedFaculty) && "công nghệ thông tin".equals(studentFaculty))
                            || ("cntt".equals(normalizedFaculty) && "cong nghe thong tin".equals(studentFaculty));
                })
                .collect(Collectors.toList());
    }

    // Lấy sinh viên theo id
    public Student getStudentById(Long id) {
        if (id == null) {
            return null;
        }
        return studentRepository.findById(id);
    }

    // Sắp xếp theo tên A-Z
    public List<Student> sortByName(List<Student> students) {
        if (students == null) {
            return List.of();
        }

        return students.stream()
                .sorted(Comparator.comparing(
                        student -> student.getFullName() == null ? "" : student.getFullName(),
                        String.CASE_INSENSITIVE_ORDER
                ))
                .collect(Collectors.toList());
    }

    // Sắp xếp theo GPA giảm dần
    public List<Student> sortByGpaDesc(List<Student> students) {
        if (students == null) {
            return List.of();
        }

        return students.stream()
                .sorted(Comparator.comparing(
                        student -> student.getGpa() == null ? 0.0 : student.getGpa(),
                        Comparator.reverseOrder()
                ))
                .collect(Collectors.toList());
    }

    // Gộp search + filter + sort
    public List<Student> getStudents(String search, String faculty, String sortBy) {
        List<Student> students = studentRepository.findAll();

        if (search != null && !search.trim().isEmpty()) {
            String lowerSearch = search.trim().toLowerCase();
            students = students.stream()
                    .filter(student -> student.getFullName() != null
                            && student.getFullName().toLowerCase().contains(lowerSearch))
                    .collect(Collectors.toList());
        }

        if (faculty != null && !faculty.trim().isEmpty()) {
            String normalizedFaculty = faculty.trim().toLowerCase();
            students = students.stream()
                    .filter(student -> {
                        if (student.getFaculty() == null) {
                            return false;
                        }

                        String studentFaculty = student.getFaculty().trim().toLowerCase();

                        return studentFaculty.equals(normalizedFaculty)
                                || ("cntt".equals(normalizedFaculty) && "công nghệ thông tin".equals(studentFaculty))
                                || ("cntt".equals(normalizedFaculty) && "cong nghe thong tin".equals(studentFaculty));
                    })
                    .collect(Collectors.toList());
        }

        if ("name".equalsIgnoreCase(sortBy)) {
            students = sortByName(students);
        } else if ("gpa".equalsIgnoreCase(sortBy)) {
            students = sortByGpaDesc(students);
        }

        return students;
    }

    // Tổng số sinh viên
    public int countTotalStudents() {
        return studentRepository.findAll().size();
    }

    // GPA trung bình
    public double calculateAverageGpa() {
        return studentRepository.findAll().stream()
                .map(Student::getGpa)
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    // Thủ khoa
    public Student findTopStudent() {
        return studentRepository.findAll().stream()
                .filter(student -> student.getGpa() != null)
                .max(Comparator.comparing(Student::getGpa))
                .orElse(null);
    }

    // Tỷ lệ trạng thái
    public Map<String, Double> calculateStatusPercentage() {
        List<Student> students = studentRepository.findAll();
        int totalStudents = students.size();

        if (totalStudents == 0) {
            return Map.of();
        }

        Map<String, Long> statusCount = students.stream()
                .collect(Collectors.groupingBy(
                        student -> student.getStatus() == null || student.getStatus().trim().isEmpty()
                                ? "Chưa rõ"
                                : student.getStatus(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ));

        Map<String, Double> statusPercentage = new LinkedHashMap<>();
        for (Map.Entry<String, Long> entry : statusCount.entrySet()) {
            double percentage = entry.getValue() * 100.0 / totalStudents;
            statusPercentage.put(entry.getKey(), percentage);
        }

        return statusPercentage;
    }
}