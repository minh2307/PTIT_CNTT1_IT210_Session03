<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Danh sách sinh viên</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        .status-active {
            color: green;
            font-weight: bold;
        }
        .status-pause {
            color: orange;
            font-weight: bold;
        }
        .status-graduated {
            color: blue;
            font-weight: bold;
        }
    </style>
</head>

<body>

<h2>Danh sách sinh viên</h2>

<!-- Thông báo -->
<c:if test="${not empty students}">
    <p>Tìm thấy <b>${students.size()}</b> sinh viên phù hợp</p>
</c:if>

<!-- Sắp xếp -->
<p>
    Sắp xếp:
    <a href="${pageContext.request.contextPath}/students?sortBy=name">Tên A-Z</a> |
    <a href="${pageContext.request.contextPath}/students?sortBy=gpa">GPA cao → thấp</a>
</p>

<table>
    <thead>
    <tr>
        <th>STT</th>
        <th>Mã SV</th>
        <th>Họ tên</th>
        <th>Khoa</th>
        <th>Năm nhập học</th>
        <th>GPA</th>
        <th>Trạng thái</th>
        <th>Chi tiết</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="student" items="${students}" varStatus="loop">
        <tr>
            <!-- STT -->
            <td>${loop.index + 1}</td>

            <td>${student.studentCode}</td>
            <td>${student.fullName}</td>
            <td>${student.faculty}</td>
            <td>${student.enrollmentYear}</td>
            <td>${student.gpa}</td>

            <!-- Trạng thái -->
            <td>
                <c:if test="${student.status == 'Đang học'}">
                    <span class="status-active">${student.status}</span>
                </c:if>

                <c:if test="${student.status == 'Bảo lưu'}">
                    <span class="status-pause">${student.status}</span>
                </c:if>

                <c:if test="${student.status == 'Tốt nghiệp'}">
                    <span class="status-graduated">${student.status}</span>
                </c:if>
            </td>

            <!-- Link chi tiết -->
            <td>
                <a href="${pageContext.request.contextPath}/students/detail?id=${student.id}">
                    Xem
                </a>
            </td>
        </tr>
    </c:forEach>

    <!-- Không có dữ liệu -->
    <c:if test="${empty students}">
        <tr>
            <td colspan="8">Không có sinh viên nào</td>
        </tr>
    </c:if>

    </tbody>
</table>

</body>
</html>