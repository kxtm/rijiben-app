<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <meta name="applicable-device" content="pc">
    <link  href="${pageContext.request.contextPath}/static/image/favicon.ico"  rel="shortcut icon" type="image/x-icon" />
    <title>首页</title>
  </head>
  <body>
  <c:forEach var="item" items="${list}">
    ${item}
  </c:forEach>
  </body>
</html>
