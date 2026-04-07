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
    <meta charset="UTF-8">
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

<form action="${pageContext.request.contextPath}/students" method="get" style="margin-bottom: 15px;">
    <input type="text" name="search" placeholder="Nhập tên sinh viên" value="${search}" />
    <input type="text" name="faculty" placeholder="Nhập khoa, ví dụ: CNTT" value="${faculty}" />
    <button type="submit">Tìm kiếm / Lọc</button>
</form>

<p>Tìm thấy ${resultCount} sinh viên phù hợp</p>

<p>
    <a href="${pageContext.request.contextPath}/students?search=${search}&faculty=${faculty}&sortBy=name">
        Sắp xếp theo tên A-Z
    </a>
    |
    <a href="${pageContext.request.contextPath}/students?search=${search}&faculty=${faculty}&sortBy=gpa">
        Sắp xếp theo GPA giảm dần
    </a>
</p>

<table border="1" cellspacing="0" cellpadding="8">
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

    <c:forEach var="student" items="${students}" varStatus="loop">
        <tr>
            <td>${loop.index + 1}</td>
            <td>${student.studentCode}</td>
            <td>${student.fullName}</td>
            <td>${student.faculty}</td>
            <td>${student.enrollmentYear}</td>
            <td>${student.gpa}</td>
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
            <td>
                <a href="${pageContext.request.contextPath}/students/detail?id=${student.id}">Xem</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="${pageContext.request.contextPath}/dashboard" class="btn btn-secondary">
    ← Quay lại dashboard
</a>

</body>
</html>
