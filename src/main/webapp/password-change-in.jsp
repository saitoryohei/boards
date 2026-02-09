<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード変更</title>
<script>
function checkPassword() {
    const pass = document.getElementById("new_password").value;
    if (pass.length < 8) {
        alert("パスワードは8文字以上で入力してください。");
        return false; // 送信をキャンセル
    }
    return true; // 送信実行
}
</script>
</head>
<body>
    <h2>管理者パスワードの変更</h2>
    <form action="password-update.action" method="post" onsubmit="return checkPassword()">
        <p>新しいパスワードを入力してください：<br>
        <input type="password" name="password" id="new_password" required></p>
        <input type="submit" value="変更を確定する">
    </form>
    <br>
    <a href="login.action">メニューに戻る</a>
</body>
</html>