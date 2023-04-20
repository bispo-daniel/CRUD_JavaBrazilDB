import javax.swing.*;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class CreateState {
    static void createState(){
        String stateName = JOptionPane.showInputDialog(null, "Type the new state's name");
        String stateAbbreviation = JOptionPane.showInputDialog(null, "Type the state's abbreviation (Max: 2 characters)");
        String stateRegion = JOptionPane.showInputDialog(null, "Type the state's region");
        String statePopulationString = JOptionPane.showInputDialog(null, "Type the state's population");
            int statePopulation = Integer.parseInt(statePopulationString);

        try {
            String sql = "INSERT INTO estado (nome, sigla, regiao, populacao) VALUES (?, ?, ?, ?)";
            PreparedStatement state = Main.myConnection.conn.prepareStatement(sql);

            state.setString(1, stateName);
            state.setString(2, stateAbbreviation);
            state.setString(3, stateRegion);
            state.setInt(4, statePopulation);

            int success = state.executeUpdate();
            if(success > 0){
                JOptionPane.showMessageDialog(null, "State successfully created!");
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        Main.menu();
    }
}