<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Dashboard - StudentHub</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>Dashboard tổng quan</h1>
        <p class="page-subtitle">Thống kê nhanh tình hình học tập của nhóm sinh viên</p>
    </div>

    <div class="dashboard-grid">
        <div class="dashboard-card">
            <h3>Tổng số sinh viên</h3>
            <div class="dashboard-value">${totalStudents}</div>
            <p class="dashboard-note">Tổng số thành viên hiện có trong danh sách</p>
        </div>

        <div class="dashboard-card">
            <h3>GPA trung bình</h3>
            <div class="dashboard-value">
                <c:choose>
                    <c:when test="${not empty averageGpa}">
                        <fmt:formatNumber value="${averageGpa}" type="number" minFractionDigits="2" maxFractionDigits="2"/>
                    </c:when>
                    <c:otherwise>0.00</c:otherwise>
                </c:choose>
            </div>
            <p class="dashboard-note">Giá trị trung bình GPA của toàn nhóm</p>
        </div>

        <div class="dashboard-card">
            <h3>Thủ khoa nhóm</h3>
            <div class="dashboard-highlight">
                <c:choose>
                    <c:when test="${not empty topStudent}">
                        <div class="top-student-name">${topStudent.fullName}</div>
                        <div class="top-student-sub">
                                ${topStudent.studentCode} - GPA: ${topStudent.gpa}
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="top-student-name">Chưa có dữ liệu</div>
                    </c:otherwise>
                </c:choose>
            </div>
            <p class="dashboard-note">Sinh viên có GPA cao nhất</p>
        </div>
    </div>

    <div class="section-card">
        <h2>Tỷ lệ sinh viên theo trạng thái</h2>

        <c:choose>
            <c:when test="${not empty statusPercentages}">
                <table class="custom-table">
                    <thead>
                    <tr>
                        <th>Trạng thái</th>
                        <th>Tỷ lệ (%)</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="entry" items="${statusPercentages}">
                        <tr>
                            <td>
                                <c:choose>
                                    <c:when test="${entry.key == 'Đang học'}">
                                        <span class="status-badge status-studying">${entry.key}</span>
                                    </c:when>
                                    <c:when test="${entry.key == 'Bảo lưu'}">
                                        <span class="status-badge status-paused">${entry.key}</span>
                                    </c:when>
                                    <c:when test="${entry.key == 'Tốt nghiệp'}">
                                        <span class="status-badge status-graduated">${entry.key}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="status-badge status-default">${entry.key}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <fmt:formatNumber value="${entry.value}" type="number" minFractionDigits="2" maxFractionDigits="2"/>%
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>

            <c:otherwise>
                <div class="empty-box">
                    <h3>Chưa có dữ liệu thống kê</h3>
                    <p>Dashboard cần controller truyền dữ liệu như totalStudents, averageGpa, topStudent, statusPercentages.</p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="button-group">
        <a href="${pageContext.request.contextPath}/students" class="btn btn-primary">Xem danh sách sinh viên</a>
    </div>
</div>
</body>
</html>