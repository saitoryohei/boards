package dao;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.sql.DataSource;
import java.sql.Connection;

public class DAO {
    // データソース（電話交換機のようなもの）を保持する変数
    private static DataSource ds;

    public Connection getConnection() throws Exception {
        if (ds == null) {
            // context.xmlから情報を探し出す
            Context context = new InitialContext();
            // java:comp/env/ はお決まりの場所、その後にcontext.xmlのnameを書く
            ds = (DataSource)context.lookup("java:comp/env/jdbc/mysql");
        }
        return ds.getConnection();
    }
}