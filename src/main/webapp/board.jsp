<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, bean.Board, bean.Customer" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板 (BOARD)</title>
<style>
    .error { color: red; font-weight: bold; }
    table { border-collapse: collapse; width: 100%; margin-top: 20px; }
    th, td { border: 1px solid #ccc; padding: 10px; text-align: left; }
    th { background-color: #f2f2f2; }
    textarea { width: 100%; height: 80px; margin-bottom: 10px; }
    .del-btn { background-color: #eee; border: 1px solid #999; cursor: pointer; border-radius: 3px; }
    .del-btn:hover { background-color: #ffcccc; }
</style>
</head>
<body>

    <h2>掲示板</h2>

    <%-- ログイン情報の取得 --%>
    <% Customer loginUser = (Customer)session.getAttribute("customer"); %>

    <%-- 1. 警告メッセージの表示 --%>
    <% 
        String errorMsg = (String)request.getAttribute("error_msg"); 
        if (errorMsg != null) { 
    %>
        <p class="error"><%= errorMsg %></p>
    <% } %>

    <%-- 2. 投稿フォーム --%>
    <form action="board.action" method="post">
        <input type="hidden" name="action_type" value="update">
        <textarea name="contents" placeholder="メッセージを入力してください"></textarea><br>
        <input type="submit" value="投稿">
    </form>

    <br>

    <%-- 3. 全件削除フォーム (管理者 role=1 のみに表示) --%>
    <% if (loginUser != null && loginUser.getRole() == 1) { %>
        <form action="board.action" method="post" onsubmit="return confirm('【管理者権限】本当に全ての投稿を削除しますか？');">
            <input type="hidden" name="action_type" value="clear">
            <input type="submit" value="全件削除 (Clear)" style="background-color: #ff4444; color: white; border: none; padding: 5px 10px; cursor: pointer;">
        </form>
    <% } %>

    <hr>

    <%-- 4. 一覧表示テーブル --%>
    <table>
        <tr>
            <th>日付</th>
            <th>ユーザーID</th>
            <th>内容</th>
            <th>操作</th> <%-- 操作列を追加 --%>
        </tr>
        <%
            List<Board> list = (List<Board>)request.getAttribute("board_list");
            if (list != null && !list.isEmpty()) {
                for (Board b : list) {
        %>
            <tr>
                <td><%= b.getDate() %></td>
                <td><%= b.getLogin_id() %></td>
                <td><%= b.getContents() %></td>
                <td>
                    <%-- 5. 個別削除ボタン（本人の投稿、または管理者の場合のみ表示） --%>
                    <% if (loginUser != null && (loginUser.getLogin().equals(b.getLogin_id()) || loginUser.getRole() == 1)) { %>
                        <form action="board.action" method="post" style="display:inline;">
                            <input type="hidden" name="action_type" value="delete">
                            <input type="hidden" name="id" value="<%= b.getId() %>">
                            <input type="submit" value="削除" class="del-btn" onclick="return confirm('この投稿を削除しますか？');">
                        </form>
                    <% } %>
                </td>
            </tr>
        <%
                }
            } else {
        %>
            <tr>
                <td colspan="4">投稿はありません。</td>
            </tr>
        <% } %>
    </table>

    <br>
    <%-- 戻るボタンの判定 --%>
    <% if (loginUser != null && loginUser.getRole() == 1) { %>
        <a href="login-out.jsp">管理者メニューに戻る (Back)</a>
    <% } else { %>
        <a href="user-menu.jsp">メニューに戻る (Back)</a>
    <% } %>

</body>
</html>