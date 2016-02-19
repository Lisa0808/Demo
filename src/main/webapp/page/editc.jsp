<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<center>
  <h2>修改客户信息页面</h2>
  <FORM METHOD=POST ACTION="/customer?operate=edit">
    <INPUT TYPE="hidden" NAME="id" value="${ customer.id }">
    <TABLE border="1" width="600">
      <TR>
        <TD>姓名</TD>
        <TD><INPUT TYPE="text" NAME="name" value="${ customer.name }"></TD>
      </TR>
      <TR>
        <TD>性别</TD>
        <TD>
          <INPUT TYPE="radio" NAME="gender" value="1"
          <c:if test="${ customer.gender == '1' }">
                 checked
          </c:if>>男<INPUT TYPE="radio" NAME="gender" value="2"
        <c:if test="${ customer.gender == '2'}">
                          checked= "checked"
        </c:if>>女</TD>
      </TR>
      <TR>
        <TD>电话</TD>
        <TD><INPUT TYPE="text" NAME="tel" value="${ customer.tel }" ></TD>
      </TR>
      <TR>
        <TD>邮箱</TD>
        <TD><INPUT TYPE="text" NAME="email" value="${ customer.email }"></TD>
      </TR>
      <TR>
        <TD>爱好</TD>
        <TD>
          <INPUT TYPE="checkbox" NAME="hobby" value="1" <c:if test="${ customer.hobby.contains('1') }">checked="1"</c:if>>高尔夫
          <INPUT TYPE="checkbox" NAME="hobby" value="2" <c:if test="${ customer.hobby.contains('2') }">checked="1"</c:if>>蹦极
          <INPUT TYPE="checkbox" NAME="hobby" value="3" <c:if test="${ customer.hobby.contains('3') }">checked="1"</c:if>>潜水
          <INPUT TYPE="checkbox" NAME="hobby" value="4" <c:if test="${ customer.hobby.contains('4') }">checked="1"</c:if>>钓鱼
        </TD>
      </TR>
      <TR>
        <TD>类型</TD>
        <TD>
          <SELECT NAME="type">
            <OPTION VALUE="1" <c:if test="${ customer.type == '1'}">selected</c:if>>普通会员
            <OPTION VALUE="2" <c:if test="${ customer.type == '2'}">selected</c:if>>青铜会员
            <OPTION VALUE="3" <c:if test="${ customer.type == '3'}">selected</c:if>>白金会员
            <OPTION VALUE="4" <c:if test="${ customer.type == '4'}">selected</c:if>>钻石会员
          </SELECT>
        </TD>
      </TR>
      <TR>
        <TD>描述</TD>
        <TD>
          <TEXTAREA NAME="description" ROWS="6" COLS="30">${ customer.description }</TEXTAREA>
        </TD>
      </TR>
      <TR>
        <TD colspan="2">
          <INPUT TYPE="submit" value="提交">
        </TD>
      </TR>
    </TABLE>
  </FORM>
</center>
</body>
</html>
