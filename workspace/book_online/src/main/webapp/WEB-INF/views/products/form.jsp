<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/book_online/produtos" method="post" enctype="multipart/form-data">
 <div>
    <label for="title">Titulo</label>
    <input type="text" name="title" id="title">
    <form:errors path="title"/> 
  </div>
  <div> 
    <label for="description">Descrição</label> 
    <textarea rows="10" cols="20" name="description" id="description"> </textarea> 
  </div>
  <div> 
    <label for="pages">Número de paginas</label> 
    <input type="text" name="pages" id="pages"/> 
  </div> 
  <div> 
    <label for="releaseDate">Data de lançamento</label> 
    <input type="date" name="releaseDate"/> 
  </div>
  <div> <label for="summary">Sumario do livro</label> 
    <input type="file" name="summary"/> 
  </div>
  <div> 
    <input type="submit" value="Enviar"> 
  </div> 
</form>
<spring:hasBindErrors name="product"> 
   <ul> 
   <c:forEach var="error" items="${errors.allErrors}"> 
   <li><spring:message code="${error.code}" text="${error.defaultMessage}"/></li> 
   </c:forEach> 
   </ul> 
</spring:hasBindErrors>
<c:forEach items="${types}" var="bookType" varStatus="status"> 
<div> <label for="price_${bookType}">${bookType}</label> 
<input type="text" name="prices[${status.index}].value" id="price_${bookType}"/> 
<input type="hidden" name="prices[${status.index}].bookType" value="${bookType}"/> 
</div> 
</c:forEach>
</body>
</html>
