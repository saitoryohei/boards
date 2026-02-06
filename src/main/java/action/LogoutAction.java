package action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import tool.Action;

// インターフェースなので implements でOK
public class LogoutAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        // 判定なしで確実に掃除して飛ばす
        session.invalidate(); 
        
        return "/logout-out.jsp"; 
    }
}