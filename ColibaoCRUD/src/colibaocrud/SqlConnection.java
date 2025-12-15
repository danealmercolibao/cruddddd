package colibaocrud;

import java.sql.*;
import javax.swing.JOptionPane;
//
public class SqlConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/colibao";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConnection(){
    Connection conn = null;
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(URL, USER, PASS);
    }catch(ClassNotFoundException | SQLException err){
        JOptionPane.showMessageDialog(null, "Error in Connection" + err);
    }
    return conn;
    }
}
