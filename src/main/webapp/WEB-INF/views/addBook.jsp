<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>addBook</title>
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

* {
  box-sizing: border-box;
}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
}

label {
  padding: 12px 12px 12px 0;
  display: inline-block;
}

input[type=submit] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  float: right;
}

input[type=submit]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}

.col-25 {
  float: left;
  width: 25%;
  margin-top: 6px;
}

.col-75 {
  float: left;
  width: 75%;
  margin-top: 6px;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}

/* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 600px) {
  .col-25, .col-75, input[type=submit] {
    width: 100%;
    margin-top: 0;
  }
}
</style>
</head>
<body>
	<div style="background-color:#f1f1f1; color: black;"><h3>Add new book</h3><hr/>
</div>
	<ul>
  <li><a href="book">AllBook</a></li>
  <li><a href="mybook">MyBook</a></li>
  <li><a href="addbook">AddBook</a></li>
  <li><a href="unreviewd">Un-reviewed</a></li>
  <li style="float:right"><a class="active" href="logout">Logout</a></li>
</ul>
<div class="container">
	<form:form modelAttribute="book">
	<div class="row">
    <div class="col-25">
      <label for="fname">Book Name</label>
    </div>
    <div class="col-75">
      <form:input path="bookName" type="text" id="fname" /><form:errors style="color:red" path="bookName" /><br/>
    </div>
  </div>
   <div class="row">
    <div class="col-25">
      <label for="lname">Author Name</label>
    </div>
    <div class="col-75">
      <form:input type="text" id="lname" path="bookAuthor" /><form:errors style="color:red" path="bookAuthor" /><br/>
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="subject">Description</label>
    </div>
    <div class="col-75">
      <form:textarea path="description"  id="subject" name="subject" style="height:200px"/><form:errors style="color:red" path="description" /><br/>
    </div>
  </div>
 

	<div class="row">
    <input type="submit" value="AddBook">
  </div>
	</form:form>
</div>

</body>
</html>