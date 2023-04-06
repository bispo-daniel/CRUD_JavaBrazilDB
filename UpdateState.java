import javax.swing.*;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class UpdateState {
    private static void updateNewUpdate(String stateName, String columnName) {
        try {
            String newValue = JOptionPane.showInputDialog(null, "Digite o novo valor");
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
                String message = "%s atualizado(a) com sucesso!";
                String formatedMsg = String.format(message, columnName);
                JOptionPane.showMessageDialog(null, formatedMsg);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    static void updateState() {
        String stateName = JOptionPane.showInputDialog(null, "Digite o nome do estado para atualizá-lo");
        String columnStr = JOptionPane.showInputDialog(null, "O que deseja atualizar?\n\n 1) Nome\n 2) Sigla\n 3) Região\n 4) População");
        int column = Integer.parseInt(columnStr);

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
                    JOptionPane.showMessageDialog(null, "Digite uma opção válida.");
                    updateState();
            }

        Main.menu();
    }

}
