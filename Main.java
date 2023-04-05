import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

public class Main {
    private static String dbUrl = "jdbc:mysql://localhost:3306/brasil";
    private static String user = "root";
    private static String pass = "1234";
    private static Connection conn;

    private static void connect(){
        try {
            conn = DriverManager.getConnection(dbUrl, user, pass);

            if(conn != null){
                JOptionPane.showMessageDialog(null, "Database connected!");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void menu(){
        try {
            String options = "Seja bem-vindo(a)\n\n 0)Sair\n 1) Criar estado\n 2) Listar estados\n 3) Atualizar estado\n 4) Excluir estado";
            String opString = JOptionPane.showInputDialog(null, options);
            int op = Integer.parseInt(opString);

            switch(op){
                case 0:
                    System.exit(0);
                        break;
                case 1:
                    createState();
                        break;
                case 2:
                    listStates();
                        break;
                case 3:
                    updateState();
                        break;
                case 4:
                    deleteState();
                        break;
                default:
                    JOptionPane.showMessageDialog(null, "Digite uma opção válida");
                    menu();
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Você pode ter digitado uma letra onde um número esperado");
            menu();
        }
    }

    private static void createState(){
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do estado");
        String sigla = JOptionPane.showInputDialog(null, "Digite a sigla do estado (Máximo: 2 Caracteres)");
        String regiao = JOptionPane.showInputDialog(null, "Digite a região do estado");
        String populacaoStr = JOptionPane.showInputDialog(null, "Digite a população do estado");
            int populacao = Integer.parseInt(populacaoStr);

        try {
            String sql = "INSERT INTO estado (nome, sigla, regiao, populacao) VALUES (?, ?, ?, ?)";
            PreparedStatement state = conn.prepareStatement(sql);

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

        menu();
    }

    private static String stateList;
    private static void listStates() {
        stateList = "";

        try {
            String sql = "Select * from estado";
            Statement state = conn.createStatement();
            ResultSet res = state.executeQuery(sql);

            while(res.next()){
                int id = res.getInt(1);
                String name = res.getString("nome");
                String sigla = res.getString(3);
                String region = res.getString("regiao");
                int population = res.getInt(5);

                String data = "Id: %d - Nome: %s - Sigla: %s - Região: %s - População: %d \n";
                stateList += String.format(data, id, name, sigla, region, population);
            }

            JOptionPane.showMessageDialog(null, stateList);
        } catch (SQLException e){
            e.printStackTrace();
        }

        menu();
    }

    private static void updateNewUpdate(String stateName, String columnName) {
        try {
            String newValue = JOptionPane.showInputDialog(null, "Digite o novo valor");
            int newPopulation;
            String formatedSQL;

            String sql = "UPDATE estado SET %s = ? WHERE nome = ?";
            formatedSQL = String.format(sql, columnName);

            PreparedStatement state = conn.prepareStatement(formatedSQL);

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

    private static void updateState() {
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

        menu();
    }

    private static void deleteState() {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do estado para excluí-lo");

        try {
            String sql = "DELETE FROM estado WHERE nome = ?";
            PreparedStatement state = conn.prepareStatement(sql);

            state.setString(1, nome);
            int deleteRow = state.executeUpdate();

            if(deleteRow > 0) {
                JOptionPane.showMessageDialog(null, "Estado excluído com sucesso!");
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        menu();
    }

    public static void main(String[] args){
        connect();
        menu();
    }
}