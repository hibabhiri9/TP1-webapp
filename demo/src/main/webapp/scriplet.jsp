<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date" %>
<html>
<head>
    <title>JSP scriplet</title>
</head>
<body>
    <h2>Current Time</h2>
    <p>
        <%
            Date d = new Date();
            out.println(d);
        %>
    </p>
    <h2>IP adress</h2>
    <p>
        <%out.println(request.getRemoteHost());%>
    </p>
</body>
</html>
