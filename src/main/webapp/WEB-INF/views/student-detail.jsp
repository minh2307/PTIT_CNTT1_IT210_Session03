<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chi tiết sinh viên - StudentHub</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>Chi tiết sinh viên</h1>
        <p class="page-subtitle">Thông tin đầy đủ của sinh viên được chọn</p>
    </div>

    <c:choose>
        <c:when test="${not empty student}">
            <div class="detail-card">
                <div class="detail-row">
                    <span class="detail-label">ID:</span>
                    <span class="detail-value">${student.id}</span>
                </div>

                <div class="detail-row">
                    <span class="detail-label">Mã sinh viên:</span>
                    <span class="detail-value">${student.studentCode}</span>
                </div>

                <div class="detail-row">
                    <span class="detail-label">Họ và tên:</span>
                    <span class="detail-value">${student.fullName}</span>
                </div>

                <div class="detail-row">
                    <span class="detail-label">Khoa:</span>
                    <span class="detail-value">${student.faculty}</span>
                </div>

                <div class="detail-row">
                    <span class="detail-label">Năm nhập học:</span>
                    <span class="detail-value">${student.enrollmentYear}</span>
                </div>

                <div class="detail-row">
                    <span class="detail-label">GPA:</span>
                    <span class="detail-value">${student.gpa}</span>
                </div>

                <div class="detail-row">
                    <span class="detail-label">Trạng thái:</span>
                    <span class="detail-value">
                        <c:choose>
                            <c:when test="${student.status == 'Đang học'}">
                                <span class="status-badge status-studying">Đang học</span>
                            </c:when>
                            <c:when test="${student.status == 'Bảo lưu'}">
                                <span class="status-badge status-paused">Bảo lưu</span>
                            </c:when>
                            <c:when test="${student.status == 'Tốt nghiệp'}">
                                <span class="status-badge status-graduated">Tốt nghiệp</span>
                            </c:when>
                            <c:otherwise>
                                <span class="status-badge status-default">${student.status}</span>
                            </c:otherwise>
                        </c:choose>
                    </span>
                </div>
            </div>
        </c:when>

        <c:otherwise>
            <div class="empty-box">
                <h3>Không tìm thấy sinh viên</h3>
                <p>Dữ liệu sinh viên chưa được truyền sang trang chi tiết hoặc id không hợp lệ.</p>
            </div>
        </c:otherwise>
    </c:choose>

    <div class="button-group">
        <a href="${pageContext.request.contextPath}/students" class="btn btn-secondary">
            ← Quay lại danh sách
        </a>
    </div>
</div>
</body>
</html>