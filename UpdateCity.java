import javax.swing.*;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class UpdateCity {
    static void updateCity() {
        String cityName = JOptionPane.showInputDialog(null, "Which city do you want to update?\n Type it's name");
        
        String options = "What do you want to update?\n\n 1) City's name\n 2) City's state id\n 3) City's population";
        String getOptionString = JOptionPane.showInputDialog(null, options);
        int column = Integer.parseInt(getOptionString);

        switch(column){
            case 1:
                updateThisCityColumn(cityName, "nome");
                break;
            case 2:
                updateThisCityColumn(cityName, "estado_id");
                break;
            case 3:
                updateThisCityColumn(cityName, "populacao");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Type a valid option...");
                Main.menu();
        }

        Main.menu();
    }

    static private void updateThisCityColumn(String cityName, String column){
        try {
            String newValue = JOptionPane.showInputDialog(null, "Type the new value");

            String sql = "UPDATE cidade SET %s = ? WHERE nome = ?";
            String formatSQL = String.format(sql, column);

            PreparedStatement state = Main.myConnection.conn.prepareStatement(formatSQL);
            
            if(column == "nome"){
                state.setString(1, newValue);
            } else {
                int newValueInt = Integer.parseInt(newValue);
                state.setInt(1, newValueInt);
            }

            state.setString(2, cityName);

            int effect = state.executeUpdate();
            if(effect > 0){
                String successMsg = "%s successfully updated";
                JOptionPane.showMessageDialog(null, String.format(successMsg, column));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}