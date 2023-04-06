import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.*;

public class DeleteCity {
    static void deleteCity() {
        try {
            String nome = JOptionPane.showInputDialog(null, "Digite o nome da cidade para excluí-la");

            String sql = "DELETE FROM cidade WHERE nome = ?";
            PreparedStatement state = Main.myConnection.conn.prepareStatement(sql);

            state.setString(1, nome);
            int effect = state.executeUpdate();

            if(effect > 0){
                String message = "%s excluído(a) com sucesso!";
                JOptionPane.showMessageDialog(null, String.format(message, nome));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        Main.menu();
    }
}
