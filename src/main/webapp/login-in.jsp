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
    ID: <input type="text" name="login"><br>
    PW: <input type="password" name="password"><br>

    <p>
        <label><input type="radio" name="role" value="0" checked> 一般ユーザー</label>
        <label><input type="radio" name="role" value="1"> 管理者</label>
    </p>

    <input type="submit" value="ログイン">
</form>
<p>アカウントをお持ちでない方は <a href="/RealExerciseServlet/register.action">新規登録</a></p>
</body>
</html>