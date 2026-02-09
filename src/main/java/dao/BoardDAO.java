package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Board;

public class BoardDAO extends DAO {

    // 1. 全件取得
    public List<Board> search() throws Exception {
        List<Board> list = new ArrayList<>();
        Connection con = getConnection();
        // 日付の新しい順（DESC）に並べるのが掲示板の定番です
        PreparedStatement st = con.prepareStatement("SELECT * FROM board ORDER BY date DESC");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Board b = new Board();
            b.setId(rs.getInt("id"));
            b.setDate(rs.getString("date"));
            b.setLogin_id(rs.getString("login_id"));
            b.setContents(rs.getString("contents"));
            list.add(b);
        }
        st.close();
        con.close();
        return list;
    }

    // 2. 新規投稿
    public int insert(Board board) throws Exception {
        Connection con = getConnection();
        // NOW() を使うとMySQL側で現在時刻を入れてくれます
        PreparedStatement st = con.prepareStatement(
            "INSERT INTO board (date, login_id, contents) VALUES (NOW(), ?, ?)");
        st.setString(1, board.getLogin_id());
        st.setString(2, board.getContents());
        int line = st.executeUpdate();
        st.close();
        con.close();
        return line;
    }

    // 3. 一件・一括削除 (Clear機能)
    
    public int deleteOne(int id) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("DELETE FROM board WHERE id = ?");
        st.setInt(1, id);
        int line = st.executeUpdate();
        
     // ★ これを絶対に入れてください！
        System.out.println("★DAO: ID [" + id + "] の削除を実行。影響した行数: " + line);
        
       
        
        st.close();
        con.close();
        return line;
    }
    
    

    public int deleteAll() throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("DELETE FROM board");
        int line = st.executeUpdate();
        st.close();
        con.close();
        return line;
    }
}