<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>welcome</title>
<style type="text/css">
body, html {margin:0; padding:0; height:100%}
#wrapper {
	margin: 0 auto;
	width: 800px;
	height: 100%;
	background: #ff9;
}
#wrapper div {
	height: 800px;
	height: 100%;
}
#wrapper div div{
	height: 25%;
}
</style>
</head>
<body>
<div style="background-color:#f1f1f1; color: black;"><h3 style="text-align:center">Welcome </h3><hr/>
</div>
	<div id="wrapper">
	<div>
		<a href="addbook"><div  style="background:#9ff"><h3 style="text-align:center">Add new Book</h3></div></a>
		<a href="book"><div  style="background:#f9f"><h3 style="text-align:center">Get All Book</h3></div></a>
		<a href="mybook"><div  style="background:#9f9"><h3 style="text-align:center">Get My Book</h3></div></a>
		<a href="unreviewd"><div  style="background:#f99"><h3 style="text-align:center">get Unreviewed Book</h3></div></a>
	</div>
</div>
</body>
</html>