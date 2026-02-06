package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// actionパッケージの中身を全部使えるようにする
import action.LoginAction;
import action.LoginAgainAction;
import action.LogoutAction;
import action.PasswordUpdateAction;
import action.RegisterAction;
import tool.Action;

@WebServlet(urlPatterns={"*.action"})
public class FrontController extends HttpServlet {
    // 共通処理メソッドを作ると確実です
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // GETの時はEncoding設定をスキップするか、判定を入れるのが安全
        if (request.getMethod().equalsIgnoreCase("POST")) {
            request.setCharacterEncoding("UTF-8");
        }
        
        // パスの取得（先頭の / を含めて判定）
        String path = request.getServletPath();
        System.out.println("★アクセスされたパス: " + path); // デバッグ用

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
            }
              else if (path.equals("/password-update.action")) {
                action = new PasswordUpdateAction();
            }

            if (action != null) {
                page = action.execute(request, response);
                System.out.println("★遷移先ページ: " + page); // デバッグ用
                request.getRequestDispatcher(page).forward(request, response);
            } else {
                System.out.println("★Actionが見つかりません: " + path);
                // どこにも該当しない場合はログイン画面へ飛ばすなどの処理
                response.sendRedirect("login-in.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }
}