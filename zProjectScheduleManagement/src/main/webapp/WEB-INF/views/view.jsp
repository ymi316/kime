<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="ko">
<head>
	<meta charset="UTF-8" />
	<title>Document</title>
</head>
<body>
	<c:if test="${empty list }">
	<h1>읽기 실패!!!</h1>
	</c:if>
	<c:if test="${not empty list }">
		<c:forEach var="vo" items="${list }">
			${vo }<br>
		</c:forEach>	
	</c:if>
</body>
</html>