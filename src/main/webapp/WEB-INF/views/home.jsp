<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<style>
body {font-family: Arial, Helvetica, sans-serif;}
form {border: 3px solid #f1f1f1;}

input[type=text], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

button {
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button:hover {
  opacity: 0.8;
}

.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
}

img.avatar {
  width: 40%;
  border-radius: 50%;
}

.container {
  padding: 16px;
}

span.psw {
  float: right;
  padding-top: 16px;
}
@media screen and (max-width: 300px) {
  span.psw {
     display: block;
     float: none;
  }
  .cancelbtn {
     width: 100%;
  }
}
</style>
</head>
<body>
<div style="background-color:#f1f1f1; color: black;" ><h2>BookishTalk</h2></div>
<div class="container">
	<form action="login.htm" method="post">
	<label for="uname"><b>Username</b></label>
	<input type="text" name="username" placeholder="Enter Username" required/><br/>
	
	<label for="psw"><b>Password</b></label>
	<input type="password" name="password" placeholder="Enter Password" required/><br/>
	<button type="submit" value="LOGIN">Login</button>
	</form>
</div>
	<div class="container" style="background-color:#f1f1f1">
    <form action="signup" method="GET">
	<button class="cancelbtn">signup</button>
	<span class="psw">create account</span>
	</form>
  </div>

</body>
</html>
