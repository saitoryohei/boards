<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者メニュー</title>
</head>
<body>

    <h2>管理者メニュー</h2>

    <p>Hello, ${customer.login} さん！</p>

    <hr>
    <ul>
        <li><a href="register-in.jsp">新規ユーザー登録</a></li>
    </ul>
    <hr>

    <p><a href="logout.action">ログアウト</a></p>

</body>
</html>