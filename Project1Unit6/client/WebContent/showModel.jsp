<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css" />
<title>Basic Car Choice</title>
</head>
<body>
<div class="container">
<h1>Basic Car Choice </h1>
<jsp:useBean id="model" scope="session" class="model.Automobile" />
<br>
<div class="container-fluid">
 <form class="form-horizontal col-sm-4" action="/client/getModel" id="optionChoice" method="post">
  <div class="form-group">
    <label class="col-sm-4 control-label text-center" for="name">Make/Model:</label>
    <div class="col-sm-8">
      <p class="form-control" type="text" id="name"> ${model.name}</p>
    </div>
  </div>
   <c:forEach items="${map}" var="entry">
   		<div class="form-group">
    <label class="col-sm-4 control-label" for="${entry.key}">${entry.key}: </label>
    <div class="col-sm-8">
    	<select class="form-control" id="${entry.key}" name="${entry.key}">
  		<c:forEach items="${entry.value}" var="val">
		    <option value="${val}">
		        ${val}
		    </option>
 		 </c:forEach>
		</select> 
    </div>
   </div>
	</c:forEach>
 <div class="form-group">
 <input class="pull-right" type="submit" >
 </div>
</form>
</div>
</div>
</body>
</html>