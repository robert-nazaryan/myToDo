<%@ page import="org.example.mytodo.model.ToDo" %>
<%@ page import="org.example.mytodo.enums.Status" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 1/23/2024
  Time: 6:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit ToDo</title>
    <style>
        input, select {
            width: 200px;
            height: 40px;
        }
    </style>
</head>
<body>
<%ToDo todo = (ToDo) request.getAttribute("todo");%>

<h3>Edin ToDo</h3>

<form action="/editToDo" method="post">
    <input type="hidden" value="<%=todo.getId()%>" name="todoId">
    <input type="text" value="<%=todo.getTitle()%>" name="todoTitle"><br>
    <input type="date" value="<%=todo.getFinishedDate()%>" name="todoFinishDate"><br>
    <select name="todoStatus">
        <%
            for (Status value : Status.values()) {
                if (todo.getStatus() == value) {
        %>
        <option value="<%=value%>" selected><%=value.name()%>
        </option>
        <%
                continue;
            }
        %>
        <option value="<%=value%>"><%=value.name()%>
        </option>
        <%}%>
    </select><br>
    <input type="submit" value="save">
</form>
</body>
</html>
