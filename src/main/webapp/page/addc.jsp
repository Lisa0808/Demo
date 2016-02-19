<%@ page import="java.util.UUID" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<center>
  <h2>增加客户信息页面</h2>

  <!-- 使用令牌防刷新 -->
  <%
    String token = UUID.randomUUID().toString();
    //将令牌存在session中
    session.setAttribute("token", token);
  %>
  <FORM METHOD=POST ACTION="/customer?operate=add">
    <TABLE border="1" width="600">
      <TR>
        <TD>姓名</TD>
        <TD><INPUT TYPE="text" NAME="name"></TD>
      </TR>
      <TR>
        <TD>性别</TD>
        <TD><INPUT TYPE="radio" NAME="gender" value="1" checked>男<INPUT TYPE="radio" NAME="gender" value="2">女</TD>
      </TR>
      <TR>
        <TD>电话</TD>
        <TD><INPUT TYPE="text" NAME="tel"></TD>
      </TR>
      <TR>
        <TD>邮箱</TD>
        <TD><INPUT TYPE="text" NAME="email"></TD>
      </TR>
      <TR>
        <TD>爱好</TD>
        <TD><INPUT TYPE="checkbox" NAME="hobby" value="1">高尔夫
          <INPUT TYPE="checkbox" NAME="hobby" value="2">蹦极
          <INPUT TYPE="checkbox" NAME="hobby" value="3">潜水
          <INPUT TYPE="checkbox" NAME="hobby" value="4">钓鱼
        </TD>
      </TR>
      <TR>
        <TD>类型</TD>
        <TD>
          <SELECT NAME="type">
            <OPTION VALUE="1" SELECTED>普通会员
            <OPTION VALUE="2">青铜会员
            <OPTION VALUE="3">白金会员
            <OPTION VALUE="4">钻石会员
          </SELECT>
        </TD>
      </TR>
      <TR>
        <TD>描述</TD>
        <TD>
          <TEXTAREA NAME="description" ROWS="6" COLS="30"></TEXTAREA>
        </TD>
      </TR>
      <TR>
        <TD colspan="2">
          <input type="hidden" name="token" value="<%=token%>">
          <INPUT TYPE="submit" value="提交">
        </TD>
      </TR>
    </TABLE>
  </FORM>
</center>
</body>
</html>
