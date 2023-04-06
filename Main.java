import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

public class Main {

    static MyConnection myConnection = new MyConnection();

    private static void menu(){
        try {
            String options = "Seja bem-vindo(a)\n\n 1) Criar estado\n 2) Listar estados\n 3) Atualizar estado\n 4) Excluir estado\n" +
                " 5) Criar cidade\n 6) Listar cidades\n 7) Atualizar cidade\n 8) Deletar cidade\n 0) Sair";
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
                case 5:
                    createCity();
                        break;
                case 6:
                    listCities();
                        break;
                case 7:
                    updateCity();
                        break;
                case 8:
                    deleteCity();
                        break;
                default:
                    JOptionPane.showMessageDialog(null, "Digite uma opção válida");
                    menu();
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Você pode ter digitado uma letra onde um número é esperado");
            menu();
        }
    }

    private static void createCity() {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome da cidade");
        String stateIdStr = JOptionPane.showInputDialog("Digite o id do estado");
            int stateId = Integer.parseInt(stateIdStr);
        String populationStr = JOptionPane.showInputDialog(null, "Digite a população da cidade");
            int population = Integer.parseInt(populationStr);

        try {
            String sql = "INSERT INTO cidade (nome, estado_id, populacao) VALUES (?, ?, ?)";
            PreparedStatement statement = myConnection.conn.prepareStatement(sql);
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

        menu();
    }

    private static String citiesList;
    private static void listCities() {
        citiesList = "";

        try {
            String sql = "SELECT c.nome, c.populacao, e.sigla FROM cidade c JOIN estado e ON c.estado_id = e.id";
            Statement state = myConnection.conn.createStatement();
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

        menu();
    }

    static private void updateThisCityColumn(String column, String cityName){
        try {
            String newValue = JOptionPane.showInputDialog(null, "Digite o novo valor");

            String sql = "UPDATE cidade SET %s = ? WHERE nome = ?";
            String formatSQL = String.format(sql, column);

            PreparedStatement state = myConnection.conn.prepareStatement(formatSQL);
            
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

    private static void updateCity() {
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
                menu();
        }

        menu();
    }

    private static void deleteCity() {
        try {
            String nome = JOptionPane.showInputDialog(null, "Digite o nome da cidade para excluí-la");

            String sql = "DELETE FROM cidade WHERE nome = ?";
            PreparedStatement state = myConnection.conn.prepareStatement(sql);

            state.setString(1, nome);
            int effect = state.executeUpdate();

            if(effect > 0){
                String message = "%s excluído(a) com sucesso!";
                JOptionPane.showMessageDialog(null, String.format(message, nome));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        menu();
    }

    private static void createState(){
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do estado");
        String sigla = JOptionPane.showInputDialog(null, "Digite a sigla do estado (Máximo: 2 Caracteres)");
        String regiao = JOptionPane.showInputDialog(null, "Digite a região do estado");
        String populacaoStr = JOptionPane.showInputDialog(null, "Digite a população do estado");
            int populacao = Integer.parseInt(populacaoStr);

        try {
            String sql = "INSERT INTO estado (nome, sigla, regiao, populacao) VALUES (?, ?, ?, ?)";
            PreparedStatement state = myConnection.conn.prepareStatement(sql);

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
            Statement state = myConnection.conn.createStatement();
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

            PreparedStatement state = myConnection.conn.prepareStatement(formatedSQL);

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
            PreparedStatement state = myConnection.conn.prepareStatement(sql);

            state.setString(1, nome);
            int deleteRow = state.executeUpdate();

            if(deleteRow > 0) {
                String msg = "%s excluído(a) com sucesso!";
                JOptionPane.showMessageDialog(null, String.format(msg, nome));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        menu();
    }

    public static void main(String[] args){
        myConnection.connect();
        menu();
    }
}