package org.example.ss03.model.repository;

import org.example.ss03.model.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {
    private final List<Student> students;

    public StudentRepository() {
        students = new ArrayList<>();

        students.add(new Student(1L, "Nguyen Van An", "B21DCCN001", "CNTT", 2021, 3.62, "Đang học"));
        students.add(new Student(2L, "Tran Thi Bich", "B21DCCN002", "CNTT", 2021, 3.74, "Đang học"));
        students.add(new Student(3L, "Le Quang Huy", "B21DCCN003", "CNTT", 2021, 3.41, "Bảo lưu"));
        students.add(new Student(4L, "Pham Thu Ha", "B21DCCN004", "CNTT", 2021, 3.85, "Đang học"));
        students.add(new Student(5L, "Do Minh Khang", "B21DCCN005", "CNTT", 2021, 3.28, "Tốt nghiệp"));
        students.add(new Student(6L, "Vu Ngoc Lan", "B21DCCN006", "CNTT", 2021, 3.67, "Đang học"));
        students.add(new Student(7L, "Bui Gia Bao", "B21DCCN007", "CNTT", 2021, 3.55, "Bảo lưu"));
        students.add(new Student(8L, "Hoang Mai Phuong", "B21DCCN008", "CNTT", 2021, 3.91, "Tốt nghiệp"));
    }

    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    public Student findById(Long id) {
        for (Student student : students) {
            if (student.getId() != null && student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }
}