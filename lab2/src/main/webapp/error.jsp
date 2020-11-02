<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="errorMessage" type="java.lang.String" scope="request" />

<html>
<head>
    <title>Error page</title>
</head>
<body>
    <h2 style="color: red">There has been an error</h2>
    <h3 style="color: red">${errorMessage}</h3>
</body>
</html>
