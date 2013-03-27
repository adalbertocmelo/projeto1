<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>parangicuricuur</title>
    </head>
    <body>
        <h3>mimimi</h3>
        <br/>
        <table>
            <tr>
                <th>Nome</th>
                <th>Idade</th>
                <th>genero</th>
                <th>profissao</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${usuario.name}"></c:out></td>
                    <td><c:out value="${usuario.age}"></c:out></td>
                    <td><c:out value="${usuario.gender}"></c:out></td>
                    <td><c:out value="${usuario.profission}"></c:out></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
