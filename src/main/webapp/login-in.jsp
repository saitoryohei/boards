<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
    <h2>ログインしてください</h2>
    <form action="login.action" method="post">
        ID：<input type="text" name="login" required><br>
        PW：<input type="password" name="password" required><br>
        <input type="submit" value="ログイン">
    </form>
</body>
</html>