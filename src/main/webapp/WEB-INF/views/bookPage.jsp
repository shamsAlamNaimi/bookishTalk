<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
}

li {
  float: left;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover:not(.active) {
  background-color: #111;
}

.active {
  background-color: #4CAF50;
}

#customers {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#customers td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4CAF50;
  color: white;
}



</style>
</head>
<body>
<div style="background-color:#f1f1f1; color: black;"><h3>List of AllBooks</h3>
</div>
<ul>
  <li><a href="book">AllBook</a></li>
  <li><a href="mybook">MyBook</a></li>
  <li><a href="addbook">AddBook</a></li>
  <li><a href="unreviewd">Un-reviewed</a></li>
  <li style="float:right"><a class="active" href="logout">Logout</a></li>
</ul>

<br/><br/>

<table id="customers">
<tr><th>Book Name</th> <th>Book Author</th> <th>Liked By</th> <th>Description</th> <th>Added By</th></tr>
<c:forEach var="book" items="${requestScope.bookList}">
<tr>
	<td>${book.bookName}</td>
	<td>${book.bookAuthor}</td>
	<td>${book.likedBy}</td>
	<td>${book.description}</td>
	<td>${book.addedBy}</td>
</tr>
</c:forEach>
</table>
<br/>
<br/>
<form action="download" method="GET">
	<button type="submit">Excel view</button>
</form>
</body>
</html>