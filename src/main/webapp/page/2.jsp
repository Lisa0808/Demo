<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<table>
  <%
    for (int x=1;x<=10;x++){
  %>
    <tr>
      <%
        for (int y=1;y<=10;y++){
      %>
            <td><%=x+"行，"+y+"列"%></td>
      <%
        }
      %>
    </tr>
  <%
    }
  %>
</table>
</body>
</html>
