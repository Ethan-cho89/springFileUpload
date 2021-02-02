<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
</head>
<body>
<table border="2">
	<tr>
		<th>FileNum</th>
		<th>Writer</th>
		<th>Title</th>
		<th>Content</th>
		<th>Original Name</th>
		<th>Saved Name</th>
		<th>FileSize</th>
		<th>Image</th>
		<th>Del/Edit</th>
		<th>Download</th>
		
	</tr>	
<c:forEach var="vo" items="${list}">
	<tr>
		<td>${vo.filenum }</td>
		<td>${vo.writer }</td>
		<td>${vo.title}</td>
		<td>${vo.content}</td>
		<td>${vo.orgfilename }</td>
		<td>${vo.savefilename }</td>
		<td>${vo.filesize }</td>
		<td><img src="${pageContext.request.contextPath}/resources/upload/${vo.savefilename}" onerror="this.style.display='none'" style="width:300; height:200px;"></td>
		<td>
			<a href="${pageContext.request.contextPath }/delete?num=${vo.filenum}">Del</a>/
			<a href="${pageContext.request.contextPath }/edit?num=${vo.filenum}">Edit</a></td>
		<td><a href="${pageContext.request.contextPath }/download?filenum=${vo.filenum}">Download</a></td>
	</tr>
</c:forEach>
</table>
<a href="<%=request.getContextPath() %>/">Home</a>
</body>
</html>