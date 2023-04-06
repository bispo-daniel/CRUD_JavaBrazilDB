import javax.swing.*;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class MyConnection {
    private static String dbUrl = "jdbc:mysql://localhost:3306/brasil";
    private static String user = "root";
    private static String pass = "1234";
    public Connection conn;

    public void connect(){
        try {
            conn = DriverManager.getConnection(dbUrl, user, pass);

            if(conn != null){
                JOptionPane.showMessageDialog(null, "Database connected!");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
