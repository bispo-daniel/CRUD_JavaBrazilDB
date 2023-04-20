import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.*;

public class DeleteCity {
    static void deleteCity() {
        try {
            String name = JOptionPane.showInputDialog(null, "Type the city's name to delete it");

            String sql = "DELETE FROM cidade WHERE nome = ?";
            PreparedStatement state = Main.myConnection.conn.prepareStatement(sql);

            state.setString(1, name);
            int effect = state.executeUpdate();

            if(effect > 0){
                String message = "%s successfully deleted!";
                JOptionPane.showMessageDialog(null, String.format(message, name));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        Main.menu();
    }
}