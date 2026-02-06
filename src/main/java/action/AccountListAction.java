package action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Customer;
import dao.CustomerDAO;
import tool.Action;

public class AccountListAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // 1. DAOを呼び出して全ユーザーリストを取得
        CustomerDAO dao = new CustomerDAO();
        List<Customer> list = dao.searchAll();

        // 2. 取得したリストをリクエスト属性にセット（JSPで使えるようにする）
        // "account_list" という名前でリストを預けます
        request.setAttribute("account_list", list);

        // 3. 表示用のJSPへ移動
        return "/account-list.jsp";
    }
}