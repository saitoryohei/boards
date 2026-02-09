package action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Customer;
import dao.CustomerDAO;
import tool.Action;

public class RegisterAction implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
       

        // 入力パラメータの取得
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        
     // ★重要：もし login が null（まだ入力してない）なら、登録画面を表示して終わる
        if (login == null) {
            return "/register-in.jsp";

        }
        // 3. Beanにまとめる
        Customer customer = new Customer();
        customer.setLogin(login);
        customer.setPassword(password);
      

        customer.setRole(0);
        // 4. DAOを使ってDBに保存
        CustomerDAO dao = new CustomerDAO();
        // ID重複などでエラーが出ても止まらないようにtry-catchしても良いですが
        // 今回はシンプルに実行し、戻り値を確認します
        
        try {
            int line = dao.insert(customer);
        
            if (line > 0) {
                // 登録成功
            	request.getSession().setAttribute("customer",customer);
                return "/user-menu.jsp";
            }
        } catch (Exception e) {
            // DBのエラー（ID重複など）
            e.printStackTrace();
        }

        // 失敗
        return "/register-error.jsp";
    }
}