package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import action.AccountListAction;
import action.BoardAction;
import action.LoginAction;
import action.LoginAgainAction;
import action.LogoutAction;
import action.PasswordUpdateAction;
import action.RegisterAction;
import tool.Action;

@WebServlet(urlPatterns={"*.action"})
public class FrontController extends HttpServlet {
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // ★ 修正点：if文を外して、常にUTF-8を設定するようにしました
        request.setCharacterEncoding("UTF-8");
        
        String path = request.getServletPath();
        System.out.println("★アクセスされたパス: " + path);

        Action action = null;
        String page = null;

        try {
            if (path.equals("/login.action")) {
                action = new LoginAction();
            } else if (path.equals("/logout.action")) {
                action = new LogoutAction();
            } else if (path.equals("/loginagain.action")) {
                action = new LoginAgainAction();
            } else if (path.equals("/register.action")) {    
                action = new RegisterAction();
            } else if (path.equals("/password-update.action")) {
                action = new PasswordUpdateAction();
            } else if (path.equals("/account-list.action")) {
                action = new AccountListAction();
            }

             else if (path.equals("/board.action")) {   
            action = new BoardAction();
            }
            
            
            if (action != null) {
                page = action.execute(request, response);
                System.out.println("★遷移先ページ: " + page);
                request.getRequestDispatcher(page).forward(request, response);
            } else {
                System.out.println("★Actionが見つかりません: " + path);
                response.sendRedirect("login-in.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    } // ← ここで doProcess 終わり

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }
}