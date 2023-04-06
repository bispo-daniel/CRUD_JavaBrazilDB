import javax.swing.*;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class CreateState {
    static void createState(){
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do estado");
        String sigla = JOptionPane.showInputDialog(null, "Digite a sigla do estado (Máximo: 2 Caracteres)");
        String regiao = JOptionPane.showInputDialog(null, "Digite a região do estado");
        String populacaoStr = JOptionPane.showInputDialog(null, "Digite a população do estado");
            int populacao = Integer.parseInt(populacaoStr);

        try {
            String sql = "INSERT INTO estado (nome, sigla, regiao, populacao) VALUES (?, ?, ?, ?)";
            PreparedStatement state = Main.myConnection.conn.prepareStatement(sql);

            state.setString(1, nome);
            state.setString(2, sigla);
            state.setString(3, regiao);
            state.setInt(4, populacao);

            int success = state.executeUpdate();
            if(success > 0){
                JOptionPane.showMessageDialog(null, "Estado criado com sucesso!");
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        Main.menu();
    }
}