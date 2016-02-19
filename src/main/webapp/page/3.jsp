<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<table>
    <%request.setAttribute("name", "才哥");%>
    ${ applicationScope.name }
    ${ requestScope.name }

    <%
        session.setAttribute("name", "chang");
        String name = (String)session.getAttribute("name");
    %>
    <%=name%>
    <br/>
    <% request.setAttribute("name1","天一"); %>
    <%= request.getAttribute("name1")%>
    ${requestScope.get("name1")}
    <%="page/pagecontext/request/response/session/application/config/out/exception"%>

    <br/>
    <%
        request.setAttribute("name2","name0000");
        response.getHeader("Date");
        session.setAttribute("name3","name3000");
        String s = config.getServletName();

    %>
</table>
</body>
</html>
