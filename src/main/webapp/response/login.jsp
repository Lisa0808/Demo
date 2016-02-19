<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<center>
  <h1>登录页面</h1>
  <form action="/responseDemo?type=login" method="post">
    <TABLE border="1" width="400">
      <TR>
        <TD>用户名</TD>
        <TD><INPUT TYPE="text" NAME="username"></TD>
      </TR>
      <TR>
        <TD>密码</TD>
        <TD><INPUT TYPE="password" NAME="password"></TD>
      </TR>
      <TR>
        <TD colspan="2"><INPUT TYPE="submit" value="提交">${msg}</TD>
      </TR>
    </TABLE>
  </form>
</center>
</body>
</html>
