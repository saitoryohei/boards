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
import action.RegisterAction;
import tool.Action;

@WebServlet(urlPatterns={"*.action"})
public class FrontController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath(); // 例: /login.action
        
        Action action = null;
        String page = null;

        try {
            // パスに合わせて担当Actionを決定
            if (path.equals("/login.action")) {
                action = new LoginAction();
            } else if (path.equals("/logout.action")) {
                action = new LogoutAction();
            } else if (path.equals("/loginagain.action")) {
                action = new LoginAgainAction();
            } else if (path.equals("/register.action")) {    
            	action = new RegisterAction();
            }

            if (action != null) {
                page = action.execute(request, response);
                request.getRequestDispatcher(page).forward(request, response);
            } else {
                // デバッグ用：どこにも引っかからなかった場合
                System.out.println("★Actionが見つかりません: " + path);
            }
                
                         
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}