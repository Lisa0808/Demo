<%--
  Created by IntelliJ IDEA.
  User: lisa-pc
  Date: 2016/1/26
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
  <form action="/requestDemo?type=upload" method="post" enctype="multipart/form-data">
    <input type="file" name="upload1"><br/>
    <input type="file" name="upload2"><br/>
    <input type="file" name="upload3"><br/>
    <input type="text" name="username"><br/>
    <input type="submit" name="submit">
    ${msg}
  </form>
</body>
</html>
