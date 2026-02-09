<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者メニュー</title>
</head>
<body>

    <h2>管理者画面ログイン中</h2>
    <p>ようこそ、<strong>${customer.login}</strong> さん</p>

    <hr>

    <ul>
        <li>
            <a href="register-in.jsp">アカウントを新規作成</a>
        </li>
        
        <li><a href="board.action">掲示板（BOARD）を確認・管理する</a></li>
        
        <li>
            <p><a href="account-list.action">ACCOUNT LIST（管理者一覧）</a></p>
        </li>
        <li>
            <a href="password-change-in.jsp">管理者パスワードの変更</a>
        </li>
        <li>
            <a href="logout.action">管理者画面をログアウトする</a>
        </li>
    </ul>

</body>
</html>