<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>refresh</title>
  <meta http-equiv="Refresh" content="5;url=/response/login.jsp">
  <script type="application/javascript">
    var num=5;
    function init(){
      var span1 = document.getElementById("span1");
      span1.innerHTML = num;
      num--;
      window.setTimeout("init()",1000);
    }
  </script>
</head>
<body onload="init();">
<h1>页面<span id="span1">5s</span>后跳转</h1>
</body>
</html>
