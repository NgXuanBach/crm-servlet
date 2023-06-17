package config;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
//Class kết nối database
public class MysqlConfig {
    private final static String url = "jdbc:mysql://localhost:3308/crm_app";
    private final static String username = "root";
    public static final String password = "admin123";

    public static Connection getConnection() {
        try {
            //chỉ định Driver sử dụng
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Tạo kết nối tới CSDL
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Lỗi kết nối tới CSDL: "+e.getMessage());
        }
        return null;
    }
}
