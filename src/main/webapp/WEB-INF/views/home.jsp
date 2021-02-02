<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home.jsp</title>
</head>
<body>

<ul>
	<li><a href="<%=request.getContextPath() %>/fileupload">파일등록하기</a></li>
	<li><a href="${pageContext.request.contextPath}/filelist">파일목록보기</a></li>
</ul>

</body>
</html>