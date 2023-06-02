package ui;

import java.util.Scanner;
import model.Game;

public class Main {

    private Scanner reader;
    private Game controller;
    private boolean finish;
    private int player;

    public Main() {
        reader = new Scanner(System.in);
        controller = new Game();
        player = 0;
    }

    public static void main(String[] args) {
        Main main = new Main();

        main.getOptionMatrixOrList();

        System.out.println("\n<<<<< Welcome to Territory Conquest Game >>>>>");

        int option = 1;

        do {
            option = main.getOptionShowMenu();
            main.executeOption(option);
        } while (option != 2);

        main.getReader().close();
    }

    public String map() {
        return ""
                + "\n**********************"
                + "\n*******EEUU***********"
                + "\n  ******************* "
                + "\n  ******************  "
                + "\n    ************      "
                + "\n      ********        "
                + "\n          ***     **  "
                + "\n            **  ***  *   **"
                + "\n              ********* ****     ***"
                + "\n             ****COL**********    "
                + "\n              *********************"
                + "\n                ****************   "
                + "\n               ******************  "
                + "\n              *******************"
                + "\n               ***************"
                + "\n                ************"
                + "\n                  ******"
                + "\n                   ****"
                + "\n                    ***"
                + "\n                     ***"
                + "\n                     **\n";
    }

     /**
     * getOptionMatrixOrList: Get the option 
     *
     * @return int
     */
    public void getOptionMatrixOrList() {
        int option = -1;
        while (option == -1) {

            System.out.println(
                    "1. Adjacency list \t" +
                    "2. Adjacency matrix\n");

            option = validateIntegerOption();
        }

        if(option == 1){
            controller.setIsMatrix(false);
        }else if(option == -2){
            controller.setIsMatrix(false);
        }

      
    }

    /**
     * getOptionMenu: Get the option of the menu
     *
     * @return int
     */
    public int getOptionShowMenu() {
        int option = -1;
        while (option == -1) {

            System.out.println(map() +
                    "1. Play \t" +
                    "2. Exit\n");

            option = validateIntegerOption();
        }

        return option;
    }

    public int getPlayOptionShowMenu() {
        int option = -1;
        while (option == -1) {

            System.out.println(
                    "1. See Conquested Territories \n" +
                            "2. See Actual Territory\n" +
                            "3. See Minimum Path to a Territory\n" +
                            "4. Conquist Territory\n" +
                            "5. Change Actual Territory\n" +
                            "6. See militar strategy (COST: 10000 TROOPS)\n" +
                            "7. See all Territories\n" +
                            "8. Give up \n");

            option = validateIntegerOption();
        }

        return option;
    }

    /**
     * executeOption: Execute the option
     *
     * @param option int - The choosen option
     */
    public void executeOption(int option) {
        switch (option) {
            case 1:
                reader.nextLine();
                System.out.println("\n Enter the name of player 1: ");
                String name1 = reader.nextLine();

                System.out.println("\n Enter the name of player 2: ");
                String name2 = reader.nextLine();

                controller.setNamesPlayers(name1, name2);

                System.out.println("\n<<GAME STARTED>>");
                int playOption = 1;
                while (playOption != 8) {
                    System.out.println("\n" + controller.getName(player).toUpperCase() + " TURN");
                    playOption = getPlayOptionShowMenu();
                    executePlayOption(playOption);
                    if (finish) {
                        break;
                    }
                }

                break;
            case 2:
                System.out.println("███▓▒░THANKS FOR PLAYING░▒▓███");
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    public void executePlayOption(int option) {
        switch (option) {
            case 1:
                // See Conquested Territories
                System.out.println(controller.printPlayerTerritories(player));
                int op = getPlayOptionShowMenu();
                executePlayOption(op);
                break;
            case 2:
                // See Actual Territory
                System.out.println(controller.actualTerritory(player));
                op = getPlayOptionShowMenu();
                executePlayOption(op);
                break;
            case 3:
                // See Minimum Path to a Territory
                reader.nextLine();
                System.out.println("\n Enter the name of territory of start");
                String territoryStart = reader.nextLine();
                System.out.println("\n Enter the name of territory of finish");
                String territoryTwo = reader.nextLine();
                System.out.println(controller.printTerritoryMinimumPath(territoryStart, territoryTwo));
                op = getPlayOptionShowMenu();
                executePlayOption(op); 
                break;
            case 4:
                // Conquist Territory
                ///FALTA VERIFICAR QUE NO CONQUISTE UNO PREVIAMENTE CONQUISTADO/////////////
                System.out.println(controller.printAdjacentsAndWeight(player));
                reader.nextLine();
                System.out.println("\n Enter the name of Territory that you want to conquist: ");
                String territory = reader.nextLine();
                System.out.println(controller.coquistTerritory(player, territory.toUpperCase()));
                if (this.player == 0) {
                    this.player = 1;
                } else {
                    this.player = 0;
                }
                break;

            case 5:
                // Change Actual Territory
                System.out.println(controller.printPlayerTerritories(player));
                reader.nextLine();
                System.out.println("\n Enter the name of Territory that you want to move you (This is free): ");
                String territoryChange = reader.nextLine();
                System.out.println(controller.moveTerritory(territoryChange.toUpperCase(), player));
                break;
            case 6:
                // See militar strategy
                System.out.println(
                        "This option allows you to see how to conquer all the territories using the least number of troops: \n ");
                System.out.println(controller.militarStrategyPlayer(player));
                op = getPlayOptionShowMenu();
                executePlayOption(op);
                break;
            case 7:
                // Show all territories
                System.out.println(controller.showAllTerritories());
                op = getPlayOptionShowMenu();
                executePlayOption(op);
                break;
            case 8:
                // Give up
                finishGame(true, player);
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    public void finishGame(boolean giveUp, int player) {
        if (giveUp) {
            finish = true;
            System.out.println("\n\n███▓▒░FINISH GAME░▒▓███\n" + controller.finishForGiveUp(player));
        }
    }

    /**
     * validateIntegerOption: This method checks if a number is an integer
     *
     * @return option - int: Returns the entered number if it is an integer or
     *         returns -1 if it is not an integer
     */
    public int validateIntegerOption() {
        int option = 0;

        if (reader.hasNextInt()) {
            option = reader.nextInt();
        } else {
            reader.nextLine();
            option = -1;
        }

        return option;
    }

    /**
     * @param reader the reader to set
     */
    public void setReader(Scanner reader) {
        this.reader = reader;
    }

    /**
     * @return Scanner return the reader
     */
    public Scanner getReader() {
        return reader;
    }
}