<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>파일수정</h1>
<!--/spring11이 컨텍스트명 (절대경로란 말) -->
<form method ="post" action="/spring11/edit" enctype="multipart/form-data"> 
	<input type="hidden" name="filenum" value="${vo.filenum}">
	작성자<br>
	<input type="text" name="writer" value="${vo.writer }"><br>
	제목<br>
	<input type="text" name="title"value="${vo.title}"><br>
	내용<br>
	<textarea rows="5" cols="50" name="content">${vo.content}</textarea><br>
	원본파일 : ${vo.orgfilename}<br><img src="${pageContext.request.contextPath}/resources/upload/${vo.savefilename}" onerror="this.style.display='none'" style="width:150px; height:100px;'"><br>
	변경파일<br>
	<input type="file" name="file1" value="${vo.savefilename}"><br>
	<input type="submit" value="확인">
</form>
</body>
</html>