import javax.swing.*;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class UpdateCity {
    static private void updateThisCityColumn(String column, String cityName){
        try {
            String newValue = JOptionPane.showInputDialog(null, "Digite o novo valor");

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
                String successMsg = "%s atualizado(a) com sucesso!";
                JOptionPane.showMessageDialog(null, String.format(successMsg, column));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    static void updateCity() {
        String nome = JOptionPane.showInputDialog(null, "Qual cidade deseja atualizar?");
        
        String options = "O que deseja alterar?\n\n 1) Nome\n 2) Id do estado\n 3) População";
        String opStr = JOptionPane.showInputDialog(null, options);
        int op = Integer.parseInt(opStr);

        switch(op){
            case 1:
                updateThisCityColumn("nome", nome);
                break;
            case 2:
                updateThisCityColumn("estado_id", nome);
                break;
            case 3:
                updateThisCityColumn("populacao", nome);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Digite uma opção válida");
                Main.menu();
        }

        Main.menu();
    }
}
