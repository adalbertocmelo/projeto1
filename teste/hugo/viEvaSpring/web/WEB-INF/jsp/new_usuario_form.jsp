<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>o papa eh pop</title>
    </head>
    <body>
        <h3>tcheco:</h3>
        <spring:nestedPath path="name">
            <form:form method="POST" commandName="usuario">
                Nome:
                <spring:bind path="name">
                    <input type="text" name="${status.expression}" value="${status.value}">
                </spring:bind>
                Idade:
                <spring:bind path="age">
                    <input type="text" name="${status.expression}" value="${status.value}">
                </spring:bind>
                Genero:
                <spring:bind path="profession">
                    <input type="text" name="${status.expression}" value="${status.value}">
                </spring:bind>
                <input type="submit" value="OK">
            </form:form>
        </spring:nestedPath>
    </body>
</html>
