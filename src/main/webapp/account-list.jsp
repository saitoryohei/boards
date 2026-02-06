<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 必要なクラスをインポートします --%>
<%@ page import="java.util.List, bean.Customer" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ACCOUNT LIST</title>
    <style>
        table { border-collapse: collapse; width: 60%; margin-top: 20px; }
        th, td { border: 1px solid #333; padding: 10px; text-align: left; }
        th { background-color: #f2f2f2; }
        tr:nth-child(even) { background-color: #fafafa; }
    </style>
</head>
<body>

    <h2>管理者アカウント一覧</h2>

    <table>
        <tr>
            <th>ID</th>
            <th>ログインID</th>
            <th>パスワード</th>
        </tr>
        <%
            // Actionから渡されたリストを取り出す
            List<Customer> list = (List<Customer>)request.getAttribute("account_list");
            
            // リストが空でない場合にループを回す
            if (list != null) {
                for (Customer c : list) {
        %>
            <tr>
                <td><%= c.getId() %></td>
                <td><%= c.getLogin() %></td>
                <td><%= c.getPassword() %></td>
            </tr>
        <%
                }
            } else {
        %>
            <tr>
                <td colspan="3">データが見つかりませんでした。</td>
            </tr>
        <%
            }
        %>
    </table>

    <br>
    <a href="login.action">メニューに戻る</a>

</body>
</html>