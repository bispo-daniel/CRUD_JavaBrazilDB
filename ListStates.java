import javax.swing.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListStates {
    private static String stateList;
    static void listStates() {
        stateList = "";

        try {
            String sql = "Select * from estado";
            Statement state = Main.myConnection.conn.createStatement();
            ResultSet res = state.executeQuery(sql);

            while(res.next()){
                int id = res.getInt(1);
                String name = res.getString("nome");
                String sigla = res.getString(3);
                String region = res.getString("regiao");
                int population = res.getInt(5);

                String data = "Id: %d - Nome: %s - Sigla: %s - Região: %s - População: %d \n";
                stateList += String.format(data, id, name, sigla, region, population);
            }

            JOptionPane.showMessageDialog(null, stateList);
        } catch (SQLException e){
            e.printStackTrace();
        }

        Main.menu();
    }
}
