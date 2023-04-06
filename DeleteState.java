import javax.swing.*;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DeleteState {
    static void deleteState() {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do estado para excluí-lo");

        try {
            String sql = "DELETE FROM estado WHERE nome = ?";
            PreparedStatement state = Main.myConnection.conn.prepareStatement(sql);

            state.setString(1, nome);
            int deleteRow = state.executeUpdate();

            if(deleteRow > 0) {
                String msg = "%s excluído(a) com sucesso!";
                JOptionPane.showMessageDialog(null, String.format(msg, nome));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        Main.menu();
    }
}
