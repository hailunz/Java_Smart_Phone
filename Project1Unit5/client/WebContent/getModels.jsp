<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css" />
<title>Available Models</title>
</head>
<body>
<div class="container">
	<h1>Available Models </h1>
<h2>Choice your model</h2>
<form action="/client/getModel" id="chooseModel" method="get">
<select  required = "required"  name="Model">
  ${data}
</select>
 <input type="submit">
</form>
</div>
</body>
</html>