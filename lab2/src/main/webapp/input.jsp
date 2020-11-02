<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="languages" type="java.util.ArrayList<java.lang.String>" scope="request" />
<html>
<head>
    <title>Input</title>

    <script>
        document.addEventListener("DOMContentLoaded", function(event) {
            let lang = window.localStorage.getItem("language");
            if (lang) {
                document.getElementById("language").value = lang;
            }
        });

        function onChange(val) {
            window.localStorage.setItem("language", val);
        }
    </script>
</head>
<body>
<form method="post" action="result">
    <label for="language">Choose a language:</label>
    <select onchange="onChange(this.value)" id="language" name="language">
        <%for (String language: languages) { %>
            <option value="<%=language%>"><%=language%></option>
        <% } %>
    </select>
    <br/><br/>
    <label for="word">Word:</label>
    <input type="text" id="word" name="word"/>
    <br/><br/>
    <label for="definition">Definition:</label>
    <textarea id="definition" name="definition" rows="4" cols="50"></textarea>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
