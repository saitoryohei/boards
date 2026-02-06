package action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Customer;
import dao.CustomerDAO;
import tool.Action;

public class LoginAction implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
    	HttpSession session = request.getSession();
        // もしセッションにすでにcustomerが入っているなら、ログイン済みとみなしてメニューへ
        if (session.getAttribute("customer") != null) {
            return "/login-out.jsp"; // (または /admin-menu.jsp)
        }
    	
    	
        // 1. 入力された値を受け取る
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        // 2. DAOを使ってDB検索
        CustomerDAO dao = new CustomerDAO();
        Customer customer = dao.search(login, password);

     // 3. 結果に応じてページを振り分ける
        if (customer != null) {
            // 成功：セッションにユーザー情報を入れる
            // (sessionは上で取得済みなので、再利用します)
            session.setAttribute("customer", customer);
            return "/login-out.jsp";
        }

        // 失敗
        return "/login-error.jsp";
    }
}