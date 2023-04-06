import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class CreateCity {
    static void createCity() {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome da cidade");
        String stateIdStr = JOptionPane.showInputDialog("Digite o id do estado");
            int stateId = Integer.parseInt(stateIdStr);
        String populationStr = JOptionPane.showInputDialog(null, "Digite a população da cidade");
            int population = Integer.parseInt(populationStr);

        try {
            String sql = "INSERT INTO cidade (nome, estado_id, populacao) VALUES (?, ?, ?)";
            PreparedStatement statement = Main.myConnection.conn.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setInt(2, stateId);
            statement.setInt(3, population);

            int rowUpdated = statement.executeUpdate();
            if(rowUpdated > 0){
                JOptionPane.showMessageDialog(null, "Cidade criada com sucesso!");
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        Main.menu();
    }
}
