<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<center>
  <h1>注册</h1>
  <FORM METHOD=GET ACTION="/requestDemo?type=sign">
    <TABLE border="1">
      <TR>
        <TD>用户名</TD>
        <TD><INPUT TYPE="text" NAME="username">
        </TD>
      </TR>
      <TR>
        <TD>密码</TD>
        <TD><INPUT TYPE="password" NAME="password">
        </TD>
      </TR>
      <TR>
        <TD>性别</TD>
        <TD>
          <INPUT TYPE="radio" NAME="sex" value="male">男
          <INPUT TYPE="radio" NAME="sex" value="female">女
          <INPUT TYPE="radio" NAME="sex" value="unknow">不详
        </TD>
      </TR>
      <TR>
        <TD>爱好</TD>
        <TD>
          <INPUT TYPE="checkbox" NAME="hobby" value="food">美食
          <INPUT TYPE="checkbox" NAME="hobby" value="game">娱乐
          <INPUT TYPE="checkbox" NAME="hobby" value="study">学习
          <INPUT TYPE="checkbox" NAME="hobby" value="sport">运动
          <INPUT TYPE="checkbox" NAME="hobby" value="girl">美女
        </TD>
      </TR>
      <TR>
        <TD>城市</TD>
        <TD><SELECT NAME="city">
          <OPTION VALUE="bj" SELECTED>北京
          <OPTION VALUE="sh">上海
          <OPTION VALUE="gz">广州
          <OPTION VALUE="sz">深圳
          <OPTION VALUE="dl">大连
          <OPTION VALUE="tj">天津
        </SELECT></TD>
      </TR>
      <TR>
        <TD>角色</TD>
        <TD><SELECT NAME="role" multiple="multiple">
          <OPTION VALUE="gly" SELECTED>管理员
          <OPTION VALUE="ls">教师
          <OPTION VALUE="xs">学生
          <OPTION VALUE="lrj">路人甲
        </SELECT></TD>
      </TR>
      <TR>
        <TD>简介</TD>
        <TD><TEXTAREA NAME="info" ROWS="6" COLS="30"></TEXTAREA></TD>
      </TR>
      <TR>
        <TD colspan="2"><INPUT TYPE="submit" value="注册">
        </TD>
      </TR>
    </TABLE>
  </FORM>
</center>
</body>
</html>
