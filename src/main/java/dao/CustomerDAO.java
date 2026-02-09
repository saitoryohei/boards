package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Customer;

public class CustomerDAO extends DAO {
    public Customer search(String login, String password,int role) throws Exception {
        Customer customer = null;

        // DAOのgetConnection()を使って接続
        Connection con = getConnection();

        // SQL文の準備（? を使うことで安全に検索）
        PreparedStatement st = con.prepareStatement(
            "select * from customer where login=? and password=? and role=?");
        st.setString(1, login);
        st.setString(2, password);
        st.setInt(3, role);
        
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setLogin(rs.getString("login"));
            customer.setPassword(rs.getString("password"));
        }

        st.close();
        con.close(); // 借りた接続を返す（重要！）
        
        return customer;
    }
    
 // 既存のsearchメソッドの下に追加してください

    public int insert(Customer customer) throws Exception {
        Connection con = getConnection();

        // ★ここが原因！
        // SQL文のカッコ内を (login, password, role) にし、
        // values を (?, ?, ?) と 3つ に増やす必要があります。
        PreparedStatement st = con.prepareStatement(
            "insert into customer(login, password, role) values(?, ?, ?)");
        
        // 1つ目：ID
        st.setString(1, customer.getLogin());
        // 2つ目：パスワード
        st.setString(2, customer.getPassword());
        // ★3つ目：role（権限）をセットする記述が足りていないはずです
        st.setInt(3, customer.getRole());

        int line = st.executeUpdate();

        st.close();
        con.close();
        return line;
    }
    
    
 // updatePassword
    public int updatePassword(int id, String newPassword) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "UPDATE customer SET password = ? WHERE id = ?"
        );
        st.setString(1, newPassword);
        st.setInt(2, id);
        
        int line = st.executeUpdate();
        st.close();
        con.close();
        return line; // 更新された行数（1なら成功）
    }
    
    public List<Customer> searchAll() throws Exception {
        List<Customer> list = new ArrayList<>();
        Connection con = getConnection();

        // 全件取得するSQL（WHERE句なし）
        PreparedStatement st = con.prepareStatement("select * from customer");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setLogin(rs.getString("login"));
            customer.setPassword(rs.getString("password"));
            
            // リストに追加
            list.add(customer);
        }

        st.close();
        con.close();
        
        return list;
    }
    
    
    
    
}