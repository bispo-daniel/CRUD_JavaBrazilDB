import javax.swing.*;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class UpdateState {
    static void updateState() {
        String stateName = JOptionPane.showInputDialog(null, "Which state do you want to update?\n Type it's name");
        
        String getOptionString = JOptionPane.showInputDialog(null, "What do you want to update?\n\n 1) State's name\n 2) State's abbreviation\n 3) State's region\n 4) State's population");
        int column = Integer.parseInt(getOptionString);

            switch(column){
                case 1:
                    updateNewUpdate(stateName, "nome");
                    break;
                case 2:
                    updateNewUpdate(stateName, "sigla");
                    break;
                case 3:
                    updateNewUpdate(stateName, "regiao");
                    break;
                case 4:
                    updateNewUpdate(stateName, "populacao");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Type a valid option...");
                    updateState();
            }

        Main.menu();
    }

    private static void updateNewUpdate(String stateName, String columnName) {
        try {
            String newValue = JOptionPane.showInputDialog(null, "Type the new value");
            int newPopulation;
            String formatedSQL;

            String sql = "UPDATE estado SET %s = ? WHERE nome = ?";
            formatedSQL = String.format(sql, columnName);

            PreparedStatement state = Main.myConnection.conn.prepareStatement(formatedSQL);

            if(columnName == "populacao"){
                newPopulation = Integer.parseInt(newValue);
                state.setInt(1, newPopulation);
            } else {
                state.setString(1, newValue);
            }

            state.setString(2, stateName);

            int rowUpdated = state.executeUpdate();
            if(rowUpdated > 0) {
                String message = "%s successfully updated!";
                String formatedMsg = String.format(message, columnName);
                JOptionPane.showMessageDialog(null, formatedMsg);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}