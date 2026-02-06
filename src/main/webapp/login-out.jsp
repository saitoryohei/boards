<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者メニュー</title>
</head>
<body>

    <h2>管理者メニュー</h2>

    <p>Hello, <strong>${customer.login}</strong> さん！</p>

    <hr>
    
    <h3>実行できる操作</h3>
    <ul>
        <li><a href="register-in.jsp">新規ユーザー登録を行う</a></li>
    </ul>

    <hr>

    <p><a href="logout.action">ログアウト</a></p>

</body>
</html>