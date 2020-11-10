<%@ page import="Beans.WordBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<jsp:useBean id="wordBean" type="Beans.WordBean" scope="request" />--%>
<jsp:useBean id="words" type="java.util.ArrayList<Beans.WordBean>" scope="request" />
<%@ taglib uri="/WEB-INF/tlds/customTags.tld" prefix="custom" %>

<html>
<head>
    <title>Result page</title>
</head>
<body>
<%--    <div>--%>
<%--        <div>Language: ${wordBean.language}</div>--%>
<%--        <br/>--%>
<%--        <div>Word: ${wordBean.word}</div>--%>
<%--        <br/>--%>
<%--        <div>Definition: ${wordBean.description}</div>--%>
<%--    </div>--%>

<table>
    <tr>
        <th>Language</th>
        <th>Word</th>
        <th>Definition</th>
    </tr>

    <%for (WordBean word: words) { %>
    <tr>
        <td><%=word.getLanguage()%></td>
        <td><%=word.getWord()%></td>
        <td><custom:definition word="<%=word.getWord()%>"/> <%=word.getDescription()%></td>
    </tr>
    <% } %>
</table>
</body>
</html>
