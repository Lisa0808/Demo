<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:set var="str1" value="this is one"></c:set>
<c:set var="str2" value="this is two"></c:set>

str1-${fn:length(str1)}
str2-${fn:length(str2)}
${fn:contains(str1, str2)}
</body>
</html>
