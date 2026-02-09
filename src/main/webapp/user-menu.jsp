<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.Customer" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>一般ユーザーメニュー</title>
</head>
<body>

    <%
        // セッションからログイン情報を取得する
        Customer c = (Customer)session.getAttribute("customer");

        // 万が一、ログインせずにこのページに来た場合の対策（念のため）
        if (c == null) {
            response.sendRedirect("login-error.jsp");
            return;
        }
    %>
    
    <h2>ようこそ、<%= c.getLogin() %> さん！</h2>
    <p>（一般ユーザーとしてログイン中）</p>

　　　　　

    <ul>
    　　<li><a href="board.action">掲示板（BOARD）へ</a></li>
        <li><a href="password-update.action">パスワード変更</a></li>
        <li><a href="logout.action">ログアウト</a></li>
    </ul>

</body>
</html>