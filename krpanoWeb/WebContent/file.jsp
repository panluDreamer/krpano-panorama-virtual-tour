<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>file test</title>
</head>

<body>
	<form action="FileUp" method="post" enctype="multipart/form-data">
		username: <input type="text" name="username"><br> file: <input
			type="file" name="file"> <input type="submit" value="submit">
	</form>
</body>
</html>