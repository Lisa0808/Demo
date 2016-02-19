<%--
  Created by IntelliJ IDEA.
  User: lisa-pc
  Date: 2016/1/22
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%="中国"%>
<%! int a=10; %>
<%
  for (int x =0;x<a;x++){
    response.getWriter().write(String.valueOf(x));
  }
%>
</body>
</html>
