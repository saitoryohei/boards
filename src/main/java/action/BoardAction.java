package action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Board;
import bean.Customer;
import dao.BoardDAO;
import tool.Action;

public class BoardAction implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        BoardDAO dao = new BoardDAO();
        HttpSession session = request.getSession();
        
        // 1. 操作タイプとログインユーザー情報の取得
        String actionType = request.getParameter("action_type");
        Customer loginUser = (Customer)session.getAttribute("customer");
        
        // --- A. 投稿処理 (update) ---
        if ("update".equals(actionType)) {
            String contents = request.getParameter("contents");
            if (contents == null || contents.trim().isEmpty()) {
                request.setAttribute("error_msg", "何か書かないとエラーですよ！内容を入力してください。");
            } else {
                Board b = new Board();
                b.setLogin_id(loginUser.getLogin());
                b.setContents(contents);
                dao.insert(b);
            }
        } 
        // --- B. 個別削除処理 (delete) ---
        else if ("delete".equals(actionType)) {
            String idStr = request.getParameter("id");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                dao.deleteOne(id);
            }
        }
        // --- C. 一括削除処理 (clear) 管理者限定 ---
        else if ("clear".equals(actionType)) {
            if (loginUser != null && loginUser.getRole() == 1) {
                dao.deleteAll();
            } else {
                request.setAttribute("error_msg", "一括削除の権限がありません。");
            }
        }
        
        // 2. 最後に常に最新の全データを取得してJSPへ
        List<Board> list = dao.search();
        request.setAttribute("board_list", list);

        return "/board.jsp";
    }
}