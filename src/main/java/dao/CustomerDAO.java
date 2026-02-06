package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import bean.Customer;

public class CustomerDAO extends DAO {
    public Customer search(String login, String password) throws Exception {
        Customer customer = null;

        // DAOのgetConnection()を使って接続
        Connection con = getConnection();

        // SQL文の準備（? を使うことで安全に検索）
        PreparedStatement st = con.prepareStatement(
            "select * from customer where login=? and password=?");
        st.setString(1, login);
        st.setString(2, password);
        
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
}