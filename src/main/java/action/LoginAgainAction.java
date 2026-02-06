package action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import tool.Action;

public class LoginAgainAction implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // セッションに残っている古い情報を破棄して、スッキリさせる
        HttpSession session = request.getSession();
        session.invalidate();
        
        // 再びログイン入力画面（login-in.jsp）へ戻す
        return "/login-in.jsp";
    }
}