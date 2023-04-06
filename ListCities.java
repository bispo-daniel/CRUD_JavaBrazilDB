import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class ListCities {
    private static String citiesList;
    static void listCities() {
        citiesList = "";

        try {
            String sql = "SELECT c.nome, c.populacao, e.sigla FROM cidade c JOIN estado e ON c.estado_id = e.id";
            Statement state = Main.myConnection.conn.createStatement();
            ResultSet res = state.executeQuery(sql);

            while(res.next()){
                String name = res.getString(1);
                int populacao = res.getInt(2);
                String sigla = res.getString(3);

                String data = "%s (%s) - População: %d \n";
                citiesList += String.format(data, name, sigla, populacao);
            }

            JOptionPane.showMessageDialog(null, citiesList);
        } catch (SQLException e){
            e.printStackTrace();
        }

        Main.menu();
    }
}
