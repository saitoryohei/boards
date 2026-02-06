<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者専用：ユーザー登録</title>
</head>
<body>

    <p>ログイン中：<strong>${customer.login}</strong> さん</p>
    <a href="logout.action">ログアウト</a>
    
    <hr>

    <h2>新規ユーザー登録</h2>
    <form action="register.action" method="post">
        <table>
            <tr><td>ログインID</td><td><input type="text" name="login" required></td></tr>
            <tr><td>パスワード</td><td><input type="password" name="password" required></td></tr>
        </table>
        <br>
        <input type="submit" value="ユーザーを新規登録する">
    </form>

</body>
</html>