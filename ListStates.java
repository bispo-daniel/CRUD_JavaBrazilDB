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
                int stateId = res.getInt(1);
                String stateName = res.getString("nome");
                String stateAbbreviation = res.getString(3);
                String stateRegion = res.getString("regiao");
                int statePopulation = res.getInt(5);

                String rowFromDB = "Id: %d - Name: %s - Abbr.: %s - Region: %s - Population: %d \n";
                stateList += String.format(rowFromDB, stateId, stateName, stateAbbreviation, stateRegion, statePopulation);
            }

            JOptionPane.showMessageDialog(null, stateList);
        } catch (SQLException e){
            e.printStackTrace();
        }

        Main.menu();
    }
}