package action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Customer;
import dao.CustomerDAO;
import tool.Action;

public class PasswordUpdateAction implements Action {
    // この execute メソッドが「Action」インターフェースで必須とされているメソッドです
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // 1. セッションから現在のログインユーザー（管理者）を取り出す
        HttpSession session = request.getSession();
        Customer customer = (Customer)session.getAttribute("customer");

        // 万が一セッションが切れていたらログイン画面へ
        if (customer == null) {
            return "/login-in.jsp";
        }

        // 2. 入力画面（password-change-in.jsp）から新しいパスワードを受け取る
        String newPassword = request.getParameter("password");
        
        // 3. DAOを使ってデータベースのパスワードを更新する
        CustomerDAO dao = new CustomerDAO();
        // ここで「メソッドがない」とエラーが出る場合は、下の「手順2」を先にやってください
        int line = dao.updatePassword(customer.getId(), newPassword);

        // 4. 更新に成功したら完了画面、失敗したらエラー画面へ
        if (line > 0) {
            // 成功したら、セッションの中のパスワード情報も新しいものに更新しておく
            customer.setPassword(newPassword);
            return "/password-change-out.jsp";
        } else {
            return "/password-error.jsp";
        }
    }
}