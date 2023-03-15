<html>
        <%--    Header    --%>
        <%@include file="common/header.jspf" %>

<body>

    <%--    Navigation Bar--%>
    <%@include file="common/navigation.jspf" %>

    <div class="container">
        <div>
            <h2>Welcome to the welcome page!</h2>
        </div>
        <div>Your name: ${username}</div>
        <div><a href="list-todos">Manage</a> your to-do items </div>
    </div>

    <%--            Script links        --%>
    <%@include file="common/scriptLinks.jspf" %>

</body>
</html>