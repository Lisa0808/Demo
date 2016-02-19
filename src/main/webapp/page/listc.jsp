<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
  <a href="/page/addc.jsp">添加客户信息</a><br/><br/>
  <c:if test="${ empty list }">
    <h1>暂无用户数据</h1>
  </c:if>
  <c:if test="${ not empty list }">
    <TABLE border="1" width="100%">
      <TR>
        <TD><input type="checkbox" id="selectAll" name="selectAll" onclick="selectAllChecked()"></TD>
        <TD>序号</TD>
        <TD>姓名</TD>
        <TD>性别</TD>
        <TD>电话</TD>
        <TD>邮箱</TD>
        <TD>爱好</TD>
        <TD>类型</TD>
        <TD>描述</TD>
        <TD>操作</TD>
      </TR>
      <c:forEach var="customer" items="${list}" varStatus="status">
        <TR>
          <TD><input type="checkbox" id="ids" name="ids" value="${ customer.id }"></TD>
          <TD>${ status.count }</TD>
          <TD>${ customer.name }</TD>
          <TD>
            <c:if test="${ customer.gender == '1'}">
              男
            </c:if>
            <c:if test="${ customer.gender == '2'}">
              女
            </c:if>
          </TD>
          <TD>${ customer.tel }</TD>
          <TD>${ customer.email }</TD>
          <TD>
            <c:if test="${ customer.hobby.contains('1') }">
              高尔夫
            </c:if>
            <c:if test="${ customer.hobby.contains('2')}">
              蹦极
            </c:if>
            <c:if test="${ customer.hobby.contains('3')}">
              潜水
            </c:if>
            <c:if test="${ customer.hobby.contains('4')}">
              钓鱼
            </c:if>
          </TD>
          <TD>
            <c:if test="${ customer.type == '1'}">
              普通会员
            </c:if>
            <c:if test="${ customer.type == '2'}">
              青铜会员
            </c:if>
            <c:if test="${ customer.type == '3'}">
              白金会员
            </c:if>
            <c:if test="${ customer.type == '4'}">
              钻石会员
            </c:if>
          </TD>
          <TD>${ customer.description }</TD>
          <TD>
            <a href="/customer?id=${ customer.id }&operate=delete">删除</a> | <a href="/customer?id=${ customer.id }&operate=editPage">修改</a>
          </TD>
        </TR>
      </c:forEach>
    </TABLE>
  </c:if>
</body>
</html>
