<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Taxi Service</title>
</head>
<body>
<header>
    <div><h1>Taxi Service</h1></div>
</header>
<form method="post" action="login" name="Login">
    <h2>Login</h2>
    <label for="loginUsername"><b>Username</b></label>
    <input type="text" placeholder="Username" name="username" id="loginUsername" required>
    <br>
    <label for="loginPassword"><b>Password</b></label>
    <input type="password" placeholder="Password" name="password" id="loginPassword" required>
    <br>
    <input type="submit" value="Login" name="login" id="login"/>
</form>
<br>

<form method="post" action="register" name="Register">
    <h2>Register</h2>
    <label for="registerUsername"><b>Username</b></label>
    <input type="text" placeholder="Username" name="username" id="registerUsername" required>
    <br>
    <label for="registerPassword"><b>Password</b></label>
    <input type="password" placeholder="Password" name="password" id="registerPassword" required>
    <br>
    <input type="submit" value="Register" name="register" id="register"/>
</form>
</body>
</html>
