import javax.swing.*;

public class Main {

    static MyConnection myConnection = new MyConnection();

    static void menu(){
        try {
            String options = "Welcome!!\n\n 1) Create state\n 2) List states\n 3) Update state\n 4) Delete state\n" +
                " 5) Create city\n 6) List cities\n 7) Update city\n 8) Delete city\n 0) Sair";
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
                    JOptionPane.showMessageDialog(null, "Type a valid option...");
                    menu();
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "You may have typed a letter where a number is expected!\n Try again...");
            menu();
        }
    }

    public static void main(String[] args){
        myConnection.connect();
        menu();
    }
}