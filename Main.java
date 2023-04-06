import javax.swing.*;

public class Main {

    static MyConnection myConnection = new MyConnection();

    static void menu(){
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
                    CreateState.createState();
                        break;
                case 2:
                    ListStates.listStates();
                        break;
                case 3:
                    UpdateState.updateState();
                        break;
                case 4:
                    DeleteState.deleteState();
                        break;
                case 5:
                    CreateCity.createCity();
                        break;
                case 6:
                    ListCities.listCities();
                        break;
                case 7:
                    UpdateCity.updateCity();
                        break;
                case 8:
                    DeleteCity.deleteCity();
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

    public static void main(String[] args){
        myConnection.connect();
        menu();
    }
}