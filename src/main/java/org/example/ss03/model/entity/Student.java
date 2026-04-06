package org.example.ss03.model.entity;

public class Student {
    private Long id;
    private String fullName;
    private String studentCode;
    private String faculty;
    private Integer enrollmentYear;
    private Double gpa;
    private String status;

    public Student() {
    }

    public Student(Long id, String fullName, String studentCode, String faculty, Integer enrollmentYear, Double gpa, String status) {
        this.id = id;
        this.fullName = fullName;
        this.studentCode = studentCode;
        this.faculty = faculty;
        this.enrollmentYear = enrollmentYear;
        this.gpa = gpa;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Integer getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(Integer enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", studentCode='" + studentCode + '\'' +
                ", faculty='" + faculty + '\'' +
                ", enrollmentYear=" + enrollmentYear +
                ", gpa=" + gpa +
                ", status='" + status + '\'' +
                '}';
    }
}
