<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

    <%@include file="common/header.jspf"%>

    <body>

        <%--    Navigation Bar--%>
        <%@include file="common/navigation.jspf" %>

        <%--        Container--%>
        <div class="container">
            <h2>Welcome, ${username}</h2>
            <div><h1>Your Todos are</h1></div>
            <table class="table">
                <thead>
                <tr>
                    <th>Description</th>
                    <th>Target Date</th>
                    <th>Is Done?</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${todos}" var="todo">
                    <tr>
                        <td>
                            ${todo.description}
                        </td>
                        <td>
                            ${todo.targetDate}
                        </td>
                        <td>
                            ${todo.done}
                        </td>
                        <td>
                            <a href="delete-todo?id=${todo.id}" class="btn btn-warning">DELETE</a>
                        </td>
                        <td>
                            <a href="update-todo?id=${todo.id}" class="btn btn-light">UPDATE</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
                <a href="add-todo" class="btn btn-success">Add Todo</a>
        </div>

        <%--            Script links        --%>
        <%@include file="common/scriptLinks.jspf" %>

    </body>
</html>