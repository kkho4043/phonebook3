<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전화번호 변경</h1>
	
	<p>전화번호를 변경하려면 <br>
	   아래 항목을 기입하고 "변경"을 누르시오</p>
	 <!--  http://localhost:8088/phonebook1/insert.jsp?name=%EA%B0%95%ED%83%9C%EC%9A%B1&hp=010-4444-4444&company=02-0202-0202-->
	<form action = "modify" method="get"> 
	이름 : <input type="text" name = "name"  value = "${pList.name}"> <br>
	핸드폰 : <input type="text" name = "hp" value = "${pList.hp}"> <br>
	회사 : <input type="text" name = "company" value = "${pList.company}"> <br>
	<input type="hidden" name = "personId" value = "${pList.personId}">
	<button type = "submit">변경</button>	
	</form>
</body>
</html>