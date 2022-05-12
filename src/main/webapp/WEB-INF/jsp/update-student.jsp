<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Update Student</title>
    </head>
    <body>
        <c:if test="${updateStudentSuccess}">
            <div>Successfully updated Student with ID: ${savedStudent.id}</div>
        </c:if>

        <c:url var="update_student_url" value="/student/updateStudent"/>
        <form:form action="${update_student_url}" method="post" modelAttribute="student">
            <form:label path="name">Student Name: </form:label> <form:input type="text" path="name"/>
            <form:label path="age">Age: </form:label> <form:input path="age"/>
            <input type="submit" value="submit"/>
        </form:form>
    </body>
</html>