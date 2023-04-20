import javax.swing.*;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DeleteState {
    static void deleteState() {
        String name = JOptionPane.showInputDialog(null, "Type the state's name to delete it");

        try {
            String sql = "DELETE FROM estado WHERE nome = ?";
            PreparedStatement state = Main.myConnection.conn.prepareStatement(sql);

            state.setString(1, name);
            int deleteRow = state.executeUpdate();

            if(deleteRow > 0) {
                String msg = "%s successfully deleted!";
                JOptionPane.showMessageDialog(null, String.format(msg, name));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        Main.menu();
    }
}
