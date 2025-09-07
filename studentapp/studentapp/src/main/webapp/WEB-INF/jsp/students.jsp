<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h2>Students List</h2>
<table border="1">
<tr><th>ID</th><th>Name</th><th>Email</th><th>Department</th><th>Actions</th></tr>
<c:forEach var="student" items="${students}">
<tr>
<td>${student.id}</td>
<td>${student.name}</td>
<td>${student.email}</td>
<td>${student.department}</td>
<td>
<a href="students/edit/${student.id}">Edit</a> |
<a href="students/delete/${student.id}">Delete</a>
</td>
</tr>
</c:forEach>
</table>
<a href="students/add">Add New Student</a>
</body>
</html>
