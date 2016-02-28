<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 되었습니다.</title>
</head>
<body>
	<c:if test = "${user != null}">
		${user.userName}님 로그인 되었습니다.
	</c:if>
	<c:if test = "${user == null}">
		로그인 해주세요.
	</c:if>

</body>
</html>