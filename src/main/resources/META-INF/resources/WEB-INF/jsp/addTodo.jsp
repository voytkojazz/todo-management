<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <%--    Header    --%>
    <%@include file="common/header.jspf" %>

    <body>

        <%--    Navigation Bar--%>
        <%@include file="common/navigation.jspf" %>

        <%--        Container       --%>
        <div class="container">
            <h1>Enter todo details</h1>

            <%--            Todo Details Form       --%>
            <form:form method="post" modelAttribute="toDo">

                <%--        Description field--%>
                <fieldset class="mb-3">
                    <form:label path="description">Description</form:label>
                    <form:input path="description" type="text" name="description" id="description"/>
                    <form:errors path="description" cssClass="text-warning"/>
                </fieldset>

                <%--        Target date field--%>
                <fieldset class="mb-3">
                    <form:label path="targetDate">Target date</form:label>
                    <form:input path="targetDate" type="text" name="targetDate" id="targetDate"/>
                    <form:errors path="targetDate" cssClass="text-warning"/>
                </fieldset>

                <form:input path="id" type="hidden" />
                <form:input path="done" type="hidden"/>

                <%--Submit button--%>
                <input type="submit" class="btn btn-success"/>
            </form:form>
        </div>

            <%--            Script links        --%>
            <%@include file="common/scriptLinks.jspf" %>

            <%--            Activate Datepicker         --%>
            <script type="text/javascript">
                $('#targetDate').datepicker({
                    format: 'yyyy-mm-dd',
                });
            </script>
    </body>
</html>