package action;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("customer") != null) {
            session.invalidate(); // セッションを掃除
            return "/logout-out.jsp";
        }
        return "/invalid-access.jsp"; 
    }
}