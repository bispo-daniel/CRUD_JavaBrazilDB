import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class CreateCity {
    static void createCity() {
        String cityName = JOptionPane.showInputDialog(null, "Type the new city's name");
        String stateIdString = JOptionPane.showInputDialog("Type the id of this city's state");
            int stateId = Integer.parseInt(stateIdString);
        String populationString = JOptionPane.showInputDialog(null, "Type the city's population");
            int population = Integer.parseInt(populationString);

        try {
            String sql = "INSERT INTO cidade (nome, estado_id, populacao) VALUES (?, ?, ?)";
            PreparedStatement statement = Main.myConnection.conn.prepareStatement(sql);
            statement.setString(1, cityName);
            statement.setInt(2, stateId);
            statement.setInt(3, population);

            int rowUpdated = statement.executeUpdate();
            if(rowUpdated > 0){
                JOptionPane.showMessageDialog(null, "City successfully created!!!");
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        Main.menu();
    }
}
